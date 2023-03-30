package com.monster.oauth;

import com.monster.core.WebAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient
public class OAuthApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(OAuthApplication.class);
        WebAutoConfiguration bean = run.getBean(WebAutoConfiguration.class);
        System.out.println("gdgs");
    }
}
