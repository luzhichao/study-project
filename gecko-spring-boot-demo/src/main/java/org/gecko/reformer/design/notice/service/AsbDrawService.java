package org.gecko.reformer.design.notice.service;


import org.gecko.reformer.design.notice.constants.EventType;
import org.gecko.reformer.design.notice.listener.MQEventListener;
import org.gecko.reformer.design.notice.listener.MessageEventListener;
import org.gecko.reformer.design.notice.manager.EventManager;

/**
 * 业务Service抽象类
 *
 * @author LZC
 * @version 1.0.0
 * @date 2021/08/21
 **/
public abstract class AsbDrawService implements IDrawService {

    private EventManager eventManager;

    public AsbDrawService() {
        eventManager = new EventManager(EventType.MQ, EventType.Message);
        eventManager.subscribe(EventType.MQ, new MQEventListener()).subscribe(EventType.Message, new MessageEventListener());
    }

    @Override
    public String draw(String id) {
        String result = doDraw(id);
        // 需要什么通知就给调⽤什么⽅法
        eventManager.notify(EventType.MQ, result).notify(EventType.Message, result);
        return result;
    }

    /**
     * 执行抽奖操作
     *
     * @param id
     * @return
     * @author LZC
     * @date 2021/08/21
     * @version 1.0.0
     **/
    protected abstract String doDraw(String id);
}
