package net.xdclass.demo;

import org.apache.catalina.filters.CorsFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


import javax.servlet.MultipartConfigElement;

//@SpringBootApplication 一个注解顶下面3个
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
@MapperScan("net.xdclass.demo.mapper")
public class XdclassApplication {

    public static void main(String[] args) {
        SpringApplication.run(XdclassApplication.class, args);
    }


    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        factory.setMaxFileSize("10240000000KB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("10240000000000KB");
        return factory.createMultipartConfig();
    }






}
