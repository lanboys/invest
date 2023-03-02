package com.bing.lan.invest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableAsync
@SpringBootApplication
@MapperScan("com.bing.lan.invest.mapper")
public class InvestApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext application = SpringApplication.run(InvestApplication.class, args);

        Environment env = application.getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                        "应用 '{}' 运行成功! \n" +
                        "----------------------------------------------------------",
                env.getProperty("spring.application.name"));

        synchronized (application) {
            try {
                application.wait();
            } catch (InterruptedException e) {
                log.error("应用运行结束...", e);
            }
        }
    }

}
