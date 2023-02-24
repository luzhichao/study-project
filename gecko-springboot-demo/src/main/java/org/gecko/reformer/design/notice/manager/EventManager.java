package org.gecko.reformer.design.notice.manager;


import org.gecko.reformer.design.notice.constants.EventType;
import org.gecko.reformer.design.notice.listener.EventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 事件处理类
 *
 * @author LZC
 * @version 1.0.0
 * @date 2021/08/21
 **/
public class EventManager {
    Map<Enum<EventType>, List<EventListener>> listeners = new HashMap<>();

    public EventManager(Enum<EventType>... operations) {
        for (Enum<EventType> operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    /**
     * 订阅
     *
     * @param eventType 事件类型
     * @param listener  监听
     */
    public EventManager subscribe(Enum<EventType> eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.add(listener);
        return this;
    }

    /**
     * 取消订阅
     *
     * @param eventType 事件类型
     * @param listener  监听
     */
    public EventManager unsubscribe(Enum<EventType> eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.remove(listener);
        return this;
    }

    /**
     * 通知
     *
     * @param eventType 事件类型
     * @param result    结果
     */
    public <T> EventManager notify(Enum<EventType> eventType, T result) {
        List<EventListener> users = listeners.get(eventType);
        for (EventListener listener : users) {
            listener.doEvent(result);
        }
        return this;
    }

}
