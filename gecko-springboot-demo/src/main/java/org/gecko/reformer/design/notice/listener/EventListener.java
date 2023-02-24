package org.gecko.reformer.design.notice.listener;

/**
 * 事件监听接⼝
 *
 * @author LZC
 * @version 1.0.0
 * @date 2021/08/21
 **/
public interface EventListener {

    <T> void doEvent(T result);
}
