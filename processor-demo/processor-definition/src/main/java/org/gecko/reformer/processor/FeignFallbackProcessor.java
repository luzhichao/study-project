package org.gecko.reformer.processor;

import cn.hutool.core.util.StrUtil;
import com.google.auto.service.AutoService;
import com.sun.source.util.TreePath;
import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Names;
import org.gecko.reformer.annotation.FeignClient;
import org.gecko.reformer.annotation.FeignFallback;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * {@link AbstractProcessor} 就属于 Pluggable Annotation Processing API
 * 调试：在processor-demo.pom目录控制台输入mvnDebug -s "/Users/luzhichao/Maven/apache-maven-3.6.0/settings_my.xml" clean install
 * 然后启动Remote JVM Debug
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-01-03
 **/
@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("org.gecko.reformer.annotation.FeignFallback")
public class FeignFallbackProcessor extends AbstractProcessor {

    /*** 文件系统 **/
    private Filer filer;
    /*** java AST 编译时代码树 **/
    private JavacTrees javacTrees;
    /*** 对 java AST 操作的工具 **/
    private TreeMaker treeMaker;
    /*** 符号封装类，统一处理名称 **/
    private Names names;
    /*** 打印信息 **/
    private Messager messager;
    /*** 返回用来在元素上进行操作的某些实用工具方法的实现。Elements是一个工具类，可以处理相关Element（包括ExecutableElement, PackageElement, TypeElement, TypeParameterElement, VariableElement） **/
    private Elements elementUtils;
    /*** 返回用来在类型上进行操作的某些实用工具方法的实现 **/
    private Types typeUtils;

    /**
     * 初始化编译时操作对象
     *
     * @param processingEnv
     * @return void
     * @throws
     * @author LZC
     * @date 2023-01-03
     * @version 1.1.2
     **/
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        javacTrees = JavacTrees.instance(processingEnv);
        final Context context = ((JavacProcessingEnvironment) processingEnv).getContext();
        treeMaker = TreeMaker.instance(context);
        names = Names.instance(context);
        messager = processingEnv.getMessager();
        filer = processingEnv.getFiler();
        elementUtils = processingEnv.getElementUtils();
        typeUtils = processingEnv.getTypeUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        final Set<? extends Element> dataAnnotations = roundEnv.getElementsAnnotatedWith(FeignFallback.class);
        for (Element element : dataAnnotations) {
            FeignClient feignClient = element.getAnnotation(FeignClient.class);
            final String s = feignClient.toString();
            if (StrUtil.contains(s, "fallbackFactory=void") && StrUtil.contains(s, "fallback=void")) {
                addAnnotationProperty(element);
            }

            //String feignClientString = feignClient.toString();
            //if (feignClientString.contains("fallbackFactory=void")) {
            //}
        }
        return true;
    }

    /**
     * 为注解添加 fallbackFactory 属性
     *
     * @param element
     * @return void
     * @throws
     * @author LZC
     * @date 2023-01-03
     * @version 1.1.2
     **/
    public void addAnnotationProperty(Element element) {
        // 创建类
        FallbackClassInfo classInfo = resolving(element);
        createSourceClass(classInfo);

        // 创建引入
        TreePath treePath = javacTrees.getPath(element);
        JCTree.JCCompilationUnit jccu = (JCTree.JCCompilationUnit) treePath.getCompilationUnit();
        JCTree.JCImport anImport = treeMaker.Import(
                treeMaker.Select(
                        treeMaker.Ident(names.fromString(classInfo.getPackageName())),
                        names.fromString(classInfo.getClassName())
                ), false);
        jccu.defs = jccu.defs.prepend(anImport);

        // 重构注解
        jccu.accept(new TreeTranslator() {
            @Override
            public void visitAnnotation(JCTree.JCAnnotation jcAnnotation) {
                super.visitAnnotation(jcAnnotation);
                jcAnnotation.args = jcAnnotation.args.append(
                        treeMaker.Assign(
                                treeMaker.Ident(names.fromString("fallbackFactory")),
                                treeMaker.Select(
                                        treeMaker.Ident(names.fromString(classInfo.getClassName())),
                                        names.fromString("class")
                                )
                        )
                );
            }
        });
    }

    /**
     * 生成 fallbackFactory 类
     *
     * @param classInfo FallbackFactory 类配置信息
     */
    public void createSourceClass(FallbackClassInfo classInfo) {
        StringBuilder builder = new StringBuilder()
                .append("package " + classInfo.getPackageName() + ";\n\n");
        for (String ipt : classInfo.getImports()) {
            builder.append("import " + ipt + ";\n");
        }
        builder.append("\n")
                .append("public class " + classInfo.getClassName() + " implements FallbackFactory<" + classInfo.getGenericsName() + "> { \n\n")
                .append("\tpublic " + classInfo.getGenericsName() + " create(Throwable var1) {\n")
                .append("\t\treturn new " + classInfo.getGenericsName() + "() {\n");
        for (String method : classInfo.getMethods()) {
            builder.append(method + "\n");
        }
        builder.append("};\n")
                .append("\t}\n")
                .append("}\n");
        try {
            JavaFileObject source = filer.createSourceFile(classInfo.getPackageName() + "." + classInfo.getClassName());
            Writer writer = source.openWriter();
            writer.write(builder.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 构建降级类数据
     *
     * @param element
     * @return org.gecko.reformer.processor.FallbackClassInfo
     * @throws
     * @author LZC
     * @date 2023-01-03
     * @version 1.1.2
     **/
    public FallbackClassInfo buildFallback(Element element) {
        TreePath treePath = javacTrees.getPath(element);
        JCTree.JCCompilationUnit jccu = (JCTree.JCCompilationUnit) treePath.getCompilationUnit();

        String className = element.getSimpleName().toString();
        String packageName = element.toString().replaceAll("." + className, "");

        FallbackClassInfo classInfo = new FallbackClassInfo();
        classInfo.setPackageName(packageName + ".fallback");
        classInfo.setClassName(className + "FallbackFactory");
        classInfo.setGenericsName(className);
        List<String> ipts = new ArrayList<>();
        classInfo.setImports(ipts);
        List<String> methods = new ArrayList<>();
        classInfo.setMethods(methods);

        // 格式化引入包名
        ipts.add("com.feign.api.FallbackFactory");
        ipts.add("com.feign.api.ProcessException");
        ipts.add(packageName + "." + className);

        JCTree.JCClassDecl classDecl = null;
        for (JCTree pTree : jccu.defs) {
            if (pTree instanceof JCTree.JCImport) {
                ipts.add(((JCTree.JCImport) pTree).getQualifiedIdentifier().toString());
            } else if (pTree instanceof JCTree.JCClassDecl) {
                classDecl = (JCTree.JCClassDecl) pTree;
            }
        }
        if (classDecl == null) {
            return null;
        }
        for (JCTree cTree : classDecl.defs) {
            if (!(cTree instanceof JCTree.JCMethodDecl)) {
                continue;
            }
            JCTree.JCMethodDecl methodDecl = (JCTree.JCMethodDecl) cTree;
            Set<String> set = methodDecl.getModifiers().getFlags().stream().map(Modifier::toString).collect(Collectors.toSet());
            if (set.contains("static") || set.contains("default")) {
                continue;
            }
            String method = "\t\t\tpublic " + methodDecl.getReturnType() + " " + methodDecl.getName() + "(" + methodDecl.getParameters() + ") {\n"
                    + "\t\t\t\tthrow new ProcessException(\"系统异常~~\");\n"
                    + "\t\t\t}\n";
            methods.add(method);
        }
        return classInfo;
    }

    /**
     * 转换降级类参数
     *
     * @param element
     * @return org.gecko.reformer.processor.FallbackClassInfo
     * @throws
     * @author LZC
     * @date 2023-01-03
     * @version 1.1.2
     **/
    public FallbackClassInfo resolving(Element element) {
        TreePath treePath = javacTrees.getPath(element);
        JCTree.JCCompilationUnit jccu = (JCTree.JCCompilationUnit) treePath.getCompilationUnit();

        String className = element.getSimpleName().toString();
        String packageName = element.toString().replaceAll("." + className, "");

        FallbackClassInfo classInfo = new FallbackClassInfo();
        classInfo.setPackageName(packageName + ".fallback");
        classInfo.setClassName(className + "FallbackFactory");
        classInfo.setGenericsName(className);
        List<String> ipts = new ArrayList<>();
        classInfo.setImports(ipts);
        List<String> methods = new ArrayList<>();
        classInfo.setMethods(methods);

        // 格式化引入包名
        ipts.add("com.feign.api.FallbackFactory");
        ipts.add("com.feign.api.ProcessException");
        ipts.add(packageName + "." + className);

        JCTree.JCClassDecl classDecl = null;
        for (JCTree pTree : jccu.defs) {
            if (pTree instanceof JCTree.JCImport) {
                ipts.add(((JCTree.JCImport) pTree).getQualifiedIdentifier().toString());
            } else if (pTree instanceof JCTree.JCClassDecl) {
                classDecl = (JCTree.JCClassDecl) pTree;
            }
        }
        if (classDecl == null) {
            return null;
        }
        for (JCTree cTree : classDecl.defs) {
            if (!(cTree instanceof JCTree.JCMethodDecl)) {
                continue;
            }
            JCTree.JCMethodDecl methodDecl = (JCTree.JCMethodDecl) cTree;
            Set<String> set = methodDecl.getModifiers().getFlags().stream().map(Modifier::toString).collect(Collectors.toSet());
            if (set.contains("static") || set.contains("default")) {
                continue;
            }
            String method = "\t\t\tpublic " + methodDecl.getReturnType() + " " + methodDecl.getName() + "(" + methodDecl.getParameters() + ") {\n"
                    + "\t\t\t\tthrow new ProcessException(\"系统异常~~\");\n"
                    + "\t\t\t}\n";
            methods.add(method);
        }
        return classInfo;
    }
}