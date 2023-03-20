//package org.gecko.reformer.kafka;
//
//import cn.hutool.core.util.StrUtil;
//import com.alibaba.google.common.collect.Lists;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.gecko.reformer.util.CollUtils;
//import org.gecko.reformer.util.SpringUtils;
//import org.reflections.Reflections;
//import org.reflections.scanners.MethodAnnotationsScanner;
//import org.reflections.util.ConfigurationBuilder;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import java.lang.reflect.Proxy;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
///**
// * TODO
// *
// * @author LZC
// * @version 1.2.0
// * @date 2023-03-20
// **/
//@Slf4j
//@Component
//@DependsOn("springUtils")
//public class KafkaListenerFactoryBeanPostProcessor implements BeanFactoryPostProcessor {
//
//    @SneakyThrows
//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//
//        List<String> packageNames = AutoConfigurationPackages.get(beanFactory);
//        String prefix = getPrefix();
//
//        for (String packageName : packageNames) {
//            Reflections reflections = new Reflections(new ConfigurationBuilder()
//                    // 指定路径URL
//                    .forPackages(packageName)
//                    //// 添加子类扫描工具
//                    //.addScanners(new SubTypesScanner())
//                    //// 添加 属性注解扫描工具
//                    //.addScanners(new FieldAnnotationsScanner())
//                    // 添加 方法注解扫描工具
//                    .addScanners(new MethodAnnotationsScanner())
//                    //// 添加方法参数扫描工具
//                    //.addScanners(new MethodParameterScanner())
//            );
//            Set<Method> methodSet = reflections.getMethodsAnnotatedWith(KafkaListener.class);
//            if (CollUtils.isNotEmptyList(methodSet)) {
//                for (Method method : methodSet) {
//                    KafkaListener kafkaListener = method.getAnnotation(KafkaListener.class);
//                    changeTopics(kafkaListener, prefix);
//                }
//            }
//        }
//    }
//
//
//    private void changeTopics(KafkaListener kafkaListener, String prefix) throws Exception {
//        final InvocationHandler invocationHandler = Proxy.getInvocationHandler(kafkaListener);
//        Field memberValuesField = invocationHandler.getClass().getDeclaredField("memberValues");
//        memberValuesField.setAccessible(true);
//        Map<String, Object> memberValues = (Map<String, Object>) memberValuesField.get(invocationHandler);
//        String[] topics = (String[]) memberValues.get("topics");
//        log.debug("修改前topics：" + Lists.newArrayList(topics));
//        for (int i = 0; i < topics.length; i++) {
//            topics[i] = prefix + StrUtil.UNDERLINE + topics[i];
//            //topics[i] = topics[i];
//        }
//        memberValues.put("topics", topics);
//        log.debug("修改后topics：" + Lists.newArrayList(kafkaListener.topics()));
//    }
//
//    private String getPrefix() {
//        final String env = SpringUtils.getProperty("spring.profiles.active", String.class, StrUtil.EMPTY);
//        String prefix = "";
//        if (StrUtil.isNotBlank(env)) {
//            prefix = env + StrUtil.UNDERLINE;
//        }
//        return prefix;
//    }
//}
