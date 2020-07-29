package ru.otus.hw09.service;

import org.springframework.data.domain.Page;
import ru.otus.hw09.model.Book;

import java.util.Optional;

public interface BookService {

  Book save(Book entity);

  Optional<Book> readBookById(String bookId);

  Book update(Book entity);

  boolean deleteBookById(String bookId);

  Page<Book> getLastAddedBooks(int count);
}
