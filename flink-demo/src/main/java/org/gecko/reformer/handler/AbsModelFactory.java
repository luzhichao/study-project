package org.gecko.reformer.handler;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.StrUtil;
import org.gecko.reformer.annotation.FlinkHandler;
import org.gecko.reformer.constant.FlinkConstants;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-09
 **/
public abstract class AbsModelFactory<T> implements IModelFactory<T> {

    @Override
    public <R> R newInstance(IHandler handler, T t) throws Exception {
        String canalTableName = getCanalTableName(handler);
        if (StrUtil.equalsIgnoreCase(FlinkConstants.ALL, canalTableName)) {
            return (R) t;
        }
        Class<R> tableClass = getTableClass(handler);
        if (tableClass != null) {
            return newInstance(tableClass, t);
        }
        return null;
    }

    public static String getCanalTableName(IHandler entryHandler) {
        FlinkHandler an = AnnotationUtil.getAnnotation(entryHandler.getClass(), FlinkHandler.class);
        if (an != null) {
            return an.jobName();
        }
        return null;
    }

    private static final Map<Class<? extends IHandler>, Class> cache = new ConcurrentHashMap<>();


    public static <T> Class<T> getTableClass(IHandler object) {
        Class<? extends IHandler> handlerClass = object.getClass();
        Class tableClass = cache.get(handlerClass);
        if (tableClass == null) {
            Type[] interfacesTypes = handlerClass.getGenericInterfaces();
            for (Type t : interfacesTypes) {
                Class c = (Class) ((ParameterizedType) t).getRawType();
                if (c.equals(IHandler.class)) {
                    tableClass = (Class<T>) ((ParameterizedType) t).getActualTypeArguments()[0];
                    cache.putIfAbsent(handlerClass, tableClass);
                    return tableClass;
                }
            }
        }
        return tableClass;
    }

    /**
     * @param c
     * @param t
     * @return R
     * @throws
     * @author LZC
     * @date 2022-06-16
     * @version 1.1.2
     **/
    abstract <R> R newInstance(Class<R> c, T t) throws Exception;
}
