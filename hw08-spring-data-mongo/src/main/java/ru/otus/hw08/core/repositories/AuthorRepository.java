package ru.otus.hw08.core.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw08.core.models.Author;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String>{

  Optional<Author> findByLastNameEquals(String authorLastName);

  Long deleteAuthorByLastNameEquals(String authorLastName);

  Long deleteAuthorById(String authorId);
}
