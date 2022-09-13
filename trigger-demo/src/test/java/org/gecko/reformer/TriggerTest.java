//package org.gecko.reformer;
//
//import lombok.extern.slf4j.Slf4j;
//import org.gecko.reformer.config.CronTaskRegistrar;
//import org.gecko.reformer.config.SchedulingRunnable;
//import org.gecko.reformer.schedule.DemoTask;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
///**
// * TODO
// *
// * @author LZC
// * @version 1.1.2
// * @date 2022-09-08
// **/
//@Slf4j
//@SpringBootTest
//public class TriggerTest {
//
//    @Autowired
//    private CronTaskRegistrar cronTaskRegistrar;
//
//    @Test
//    public void testTask() throws InterruptedException {
//        SchedulingRunnable task = new SchedulingRunnable(DemoTask.class, "taskNoParams");
//        cronTaskRegistrar.addCronTask(task, "0/10 * * * * ?");
//
//        // 便于观察
//        Thread.sleep(20000);
//    }
//
//    @Test
//    public void testHaveParamsTask() throws InterruptedException {
//        SchedulingRunnable task = new SchedulingRunnable(DemoTask.class, "taskWithParams", "haha", 23);
//        cronTaskRegistrar.addCronTask(task, "0/10 * * * * ?");
//
//        // 便于观察
//        Thread.sleep(3000000);
//    }
//}
