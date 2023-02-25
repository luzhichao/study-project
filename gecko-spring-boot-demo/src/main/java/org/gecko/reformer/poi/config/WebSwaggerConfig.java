package org.gecko.reformer.poi.config;//package org.gecko.reformer.poi.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * TODO
// *
// * @author LZC
// * @version 1.1.2
// * @date 2022-11-10
// **/
//@Configuration
//public class WebSwaggerConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        //// 解决静态资源无法访问
//        //registry.addResourceHandler("/**")
//        //        .addResourceLocations("classpath:/static/");
//        //// 解决swagger无法访问
//        //registry.addResourceHandler("/swagger-ui.html")
//        //        .addResourceLocations("classpath:/META-INF/resources/");
//        //// 解决swagger的js文件无法访问
//        //registry.addResourceHandler("/webjars/**")
//        //        .addResourceLocations("classpath:/META-INF/resources/webjars/");
//        //过滤swagger
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//        registry.addResourceHandler("/swagger-resources/**")
//                .addResourceLocations("classpath:/META-INF/resources/swagger-resources/");
//        registry.addResourceHandler("/swagger/**")
//                .addResourceLocations("classpath:/META-INF/resources/swagger*");
//        registry.addResourceHandler("/v2/api-docs/**")
//                .addResourceLocations("classpath:/META-INF/resources/v2/api-docs/");
//
//        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//
//    }
//}
