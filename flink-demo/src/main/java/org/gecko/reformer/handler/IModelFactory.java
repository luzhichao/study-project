package org.gecko.reformer.handler;

/**
 * 对象工厂
 *
 * @author LZC
 * @version 1.1.2
 * @date 2023-03-09
 **/
public interface IModelFactory<T> {

    /**
     * 构建对象
     *
     * @param handler
     * @param t
     * @return R
     * @throws Exception 异常
     * @author LZC
     * @date 2023-03-09
     * @version 1.1.2
     **/
    <R> R newInstance(IHandler handler, T t) throws Exception;
}
