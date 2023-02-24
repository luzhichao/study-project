package org.gecko.reformer.design.simpleFactory;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.gecko.reformer.design.simpleFactory.entity.AbsEntity;
import org.gecko.reformer.design.simpleFactory.entity.EntityA;
import org.gecko.reformer.design.simpleFactory.entity.EntityB;
import org.gecko.reformer.design.simpleFactory.entity.EntityC;

/**
 * 简单工厂类创建的所有对象的父类，封装了创建各种对象的公有方法，工厂类只需要定义一个通用的工厂方法，因为其创建的具体对象都是其子类对象,
 * 使用和创建分离，使系统更符合单一职责原则，工厂类的重载方法通过不同方法名表明不同构造方法创建对象的功能
 * 简单工厂模式的创建对象逻辑全部放在了工厂角色中
 *
 * @author LZC
 * @version 1.0.0
 * @date 2022-06-25
 **/
public class SimpleFactory {

    public static AbsEntity getEntity(String type, String text) {
        AbsEntity product = null;
        if (StrUtil.equalsIgnoreCase(type, "EntityA")) {
            product = JSONUtil.toBean(text, EntityA.class);
        } else if (StrUtil.equalsIgnoreCase(type, "EntityB")) {
            product = JSONUtil.toBean(text, EntityB.class);
        } else if (StrUtil.equalsIgnoreCase(type, "EntityC")) {
            product = new EntityC(text);
        }
        return product;

    }
}
