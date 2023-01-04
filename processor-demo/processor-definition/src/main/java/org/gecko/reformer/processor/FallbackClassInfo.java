package org.gecko.reformer.processor;

import java.io.Serializable;
import java.util.List;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-01-03
 **/
public class FallbackClassInfo implements Serializable {
    private static final long serialVersionUID = 8761278527492189800L;
    /**
     * 所在包名
     */
    private String packageName;
    /**
     * 需要引入的包
     */
    private List<String> imports;
    /**
     * 创建的类名称
     */
    private String className;
    /**
     * 泛型的名称
     */
    private String genericsName;
    /**
     * 方法列表
     */
    private List<String> methods;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<String> getImports() {
        return imports;
    }

    public void setImports(List<String> imports) {
        this.imports = imports;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getGenericsName() {
        return genericsName;
    }

    public void setGenericsName(String genericsName) {
        this.genericsName = genericsName;
    }

    public List<String> getMethods() {
        return methods;
    }

    public void setMethods(List<String> methods) {
        this.methods = methods;
    }
}
