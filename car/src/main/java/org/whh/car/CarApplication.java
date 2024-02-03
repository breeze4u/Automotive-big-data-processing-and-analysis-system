package org.whh.car;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.whh.car.mapper")
public class CarApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarApplication.class, args);
    }

}
