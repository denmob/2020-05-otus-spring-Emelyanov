package ru.otus.hw08.core.service;

import ru.otus.hw08.core.models.Genre;

import java.util.Optional;

public interface CRUDServiceGenre {
  Genre create(Genre entity);

  Optional<Genre> readNameEquals(String id);

  Genre update(Genre entity);

  boolean deleteNameEquals(String name);
}
