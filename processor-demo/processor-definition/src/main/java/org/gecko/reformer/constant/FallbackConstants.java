package org.gecko.reformer.constant;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 自动降级处理器常量
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-01-04
 **/
public interface FallbackConstants {

    /*** 写入包名模版 **/
    String WRITER_PACKAGE_TEMPLATE = "package {};\n\n";
    /*** 写入引入模版 **/
    String WRITER_IMPORT_TEMPLATE = "import {};\n";
    /*** 写入注解模版 **/
    String WRITER_ANNOTATIONS_TEMPLATE = "{}\n";
    /*** 写入类模版 **/
    String WRITER_CLASS_TEMPLATE = "public class {} implements {} ";
    /*** 写入方法模版 **/
    String WRITER_METHOD_TEMPLATE = "{}\n";

    /*** 需要更新的注解名称 **/
    String CHANGE_ANNOTATION_NAME = "FeignClient";
    /*** 需要更新的注解属性 **/
    String CHANGE_ANNOTATION_PROPERTIES = "fallback";
    /*** 需要更新的注解属性值 **/
    String CHANGE_ANNOTATION_PROPERTIES_VALUE = "class";

    /*** 无降级工厂类 **/
    String NON_FALLBACK_FACTORY = "fallbackFactory=void";
    /*** 无降级类 **/
    String NON_FALLBACK = "fallback=void";

    /*** 降级类包名模版 **/
    String FALLBACK_PACKAGE_NAME_TEMPLATE = "{}.fallback";
    /*** 降级类名模版 **/
    String FALLBACK_CLASS_NAME_TEMPLATE = "{}Fallback";

    /*** 固定标注注解 **/
    List<String> FIXED_ANNOTATION_LIST = Lists.newArrayList("@Slf4j", "@Component");

    /*** 固定引入依赖 **/
    List<String> FIXED_IMPORT_LIST = Lists.newArrayList("org.gecko.reformer.exception.CustomException",
            "lombok.extern.slf4j.Slf4j",
            "org.springframework.stereotype.Component",
            "java.util.*");

    /*** 操作类（新增、修改、删除）方法模版 **/
    String OPERATION_METHOD_TEMPLATE = "\t\t\t@Override\n"
            + "\t\t\tpublic {} {} ({}) {\n"
            + "\t\t\t\tlog.error(\"===服务降级===\");\n"
            + "\t\t\t\tthrow new CustomException(\"服务异常，请稍后再试或联系管理员。\");\n"
            + "\t\t\t}\n\n";
    /*** 查询类方法模版 **/
    String QUERY_METHOD_TEMPLATE = "\t\t\t@Override\n"
            + "\t\t\tpublic {} {} ({}) {\n"
            + "\t\t\t\tlog.error(\"===服务降级===\");\n"
            + "\t\t\t\t{};\n"
            + "\t\t\t}\n\n";

    /*** 操作类方法名 **/
    String[] OPERATION_METHOD_NAME = {"save", "add", "insert", "update", "del", "delete"};

    /*** 返回类型List **/
    String RETURN_TYPE_LIST = "List";
    /*** 返回类型Set **/
    String RETURN_TYPE_SET = "Set";
    /*** 返回类型Map **/
    String RETURN_TYPE_MAP = "Map";
    /*** 返回类型void **/
    String RETURN_TYPE_VOID = "void";

    /*** 返回ArrayList类型 **/
    String RETURN_ARRAY_LIST = "return new ArrayList<>()";
    /*** 返回HashMap类型 **/
    String RETURN_HASH_MAP = "return new HashMap<>()";
    /*** 返回HashSet类型 **/
    String RETURN_HASH_SET = "return new HashSet<>()";
    /*** 返回Obj类型 **/
    String RETURN_NEW_OBJ = "return new {}()";
}
