package cn.wisdsoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableHystrix
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class StudentViewApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentViewApplication.class, args);
    }

}
