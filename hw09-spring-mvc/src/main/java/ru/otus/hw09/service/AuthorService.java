package ru.otus.hw09.service;

import ru.otus.hw09.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

  Author save(Author entity);

  Optional<Author> findByLastNameEquals(String authorLastName);

  boolean deleteAuthorByLastNameEquals(String authorLastName);

  boolean deleteAuthorById(String authorId);

  List<Author> findAll();
}
