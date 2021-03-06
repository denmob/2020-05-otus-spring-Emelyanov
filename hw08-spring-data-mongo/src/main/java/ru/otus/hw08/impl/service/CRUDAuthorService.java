package ru.otus.hw08.impl.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw08.core.models.Author;
import ru.otus.hw08.core.repositories.AuthorRepository;
import ru.otus.hw08.core.service.CRUDServiceAuthor;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CRUDAuthorService implements CRUDServiceAuthor {

  private final AuthorRepository authorRepository;

  @Override
  @Transactional
  public Author create(Author entity) {
    return authorRepository.save(entity);
  }

  @Override
  public Optional<Author> readLastNameEquals(String lastName) {
    return authorRepository.findByLastNameEquals(lastName);
  }

  @Override
  @Transactional
  public boolean delete(String id) {
    return authorRepository.deleteAuthorById(id) == 1L;
  }

  @Override
  @Transactional
  public Author update(Author entity) {
    return authorRepository.save(entity);
  }
}
