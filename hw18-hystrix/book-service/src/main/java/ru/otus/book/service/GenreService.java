package ru.otus.book.service;

import ru.otus.library.model.Genre;

public interface GenreService {

  Genre getGenreByName(String name);
}
