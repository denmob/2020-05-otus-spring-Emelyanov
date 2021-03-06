package ru.otus.hw08.core.service;

import ru.otus.hw08.core.models.Author;

import java.util.Optional;

public interface CRUDServiceAuthor{

  Author create(Author entity);

  Optional<Author> readLastNameEquals(String id);

  Author update(Author entity);

  boolean delete(String id);
}
