package ru.otus.hw09.service;

import ru.otus.hw09.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
  Genre save(Genre entity);

  Optional<Genre> readNameEquals(String id);

  boolean deleteNameEquals(String name);

  List<Genre> findAll();
}
