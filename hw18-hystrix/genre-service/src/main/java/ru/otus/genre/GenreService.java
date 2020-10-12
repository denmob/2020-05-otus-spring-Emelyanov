package ru.otus.genre;

import com.github.cloudyrock.spring.v5.EnableMongock;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongock
@SpringBootApplication
@EnableMongoRepositories
@EnableConfigurationProperties
public class GenreService {

  @SneakyThrows
  public static void main(String[] args) {
    SpringApplication.run(GenreService.class, args);
  }
}
