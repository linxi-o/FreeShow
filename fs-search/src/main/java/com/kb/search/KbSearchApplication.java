package com.kb.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author syg
 * @version 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.kb.*"}, scanBasePackageClasses ={KbSearchApplication.class})
@EnableDiscoveryClient
public class KbSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(KbSearchApplication.class, args);
    }

}
