package cn.wisdsoft;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"cn.wisdsoft.mapper"})
//开启定时器任务
@EnableScheduling
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)
public class AdministratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdministratorApplication.class, args);
    }
}
