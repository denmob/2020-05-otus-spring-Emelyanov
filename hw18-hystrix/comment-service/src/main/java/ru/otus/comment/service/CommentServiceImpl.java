package ru.otus.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.comment.repository.CommentRepository;
import ru.otus.library.model.Comment;


import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;

  @Override
  @Transactional
  public Comment save(Comment entity) {
    return commentRepository.save(entity);
  }

  @Override
  public List<Comment> readCommentaryContains(String partComment) {
    return commentRepository.findCommentByCommentaryContains(partComment);
  }

  @Override
  public List<Comment> readAllForBook(String bookId) {
    return commentRepository.findAllByBookId(bookId);
  }

  @Override
  @Transactional
  public boolean deleteCommentaryContains(String partComment) {
    return (commentRepository.deleteCommentByCommentaryContains(partComment)==1L);
  }

  @Override
  public boolean deleteCommentAllByBookId(String bookId) {
    return commentRepository.deleteCommentAllByBookId(bookId)==1L;
  }
}
