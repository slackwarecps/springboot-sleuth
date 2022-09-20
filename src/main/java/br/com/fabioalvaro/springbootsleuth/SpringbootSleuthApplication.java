package br.com.fabioalvaro.springbootsleuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringbootSleuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSleuthApplication.class, args);
    }

}
