package ru.otus.book;

import com.github.cloudyrock.spring.v5.EnableMongock;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.otus.library.SharedLibrary;

@EnableHystrix
@EnableMongock
@EnableEurekaClient
@EnableCircuitBreaker
@SpringBootApplication
@EnableDiscoveryClient
@EnableMongoRepositories
@EnableConfigurationProperties
@Import(SharedLibrary.class)
@EnableFeignClients(basePackages = {"ru.otus.library.feign"})
public class BookServiceApplication {

  @SneakyThrows
  public static void main(String[] args) {
    SpringApplication.run(BookServiceApplication.class, args);
  }
}

