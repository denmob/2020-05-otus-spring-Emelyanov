package ru.otus.hw09;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class Hw09Application {

  @SneakyThrows
  public static void main(String[] args) {
    SpringApplication.run(Hw09Application.class, args);
  }
}

