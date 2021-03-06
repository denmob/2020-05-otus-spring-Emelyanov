package ru.otus.hw07.impl.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Controller;
import ru.otus.hw07.core.controller.LibraryController;
import ru.otus.hw07.core.dto.BookWithComments;
import ru.otus.hw07.core.models.Book;
import ru.otus.hw07.core.models.Comment;
import ru.otus.hw07.core.service.InputReaderService;
import ru.otus.hw07.core.service.ViewRepositoryService;
import ru.otus.hw07.impl.service.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Controller
@ShellComponent
@RequiredArgsConstructor
public class ShellLibraryController implements LibraryController {

  private final CRUDBookService crudBookService;
  private final CRUDCommentService crudCommentService;
  private final CRUDAuthorService crudAuthorService;
  private final CRUDGenreService crudGenreService;
  private final InputReaderService inputReaderService;
  private final ConsolePrintService consolePrintService;

  private final ViewRepositoryService viewRepositoryService;

  private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy MM dd");
  private static final String SUCCESS_OPERATION = "Success operation";
  private static final String FAILURE_OPERATION = "Failure operation";
  private static final String ILLEGAL_ARGUMENTS = "Illegal Arguments";

  @Override
  @ShellMethod(value = "Create book command.", key = {"cb", "createBook"})
  public String createBook() {
    Book book = processingBookInteractiveStyle(new Book());
    if (crudBookService.create(book) != null) {
      return SUCCESS_OPERATION;
    }
    return FAILURE_OPERATION;
  }

  @Override
  @ShellMethod(value = "Read book command. Format input: bookId", key = {"rb", "readBook"})
  public String readBook(long bookId) {
    if (bookId > 0) {
      Optional<BookWithComments> optionalBookWithComments = crudBookService.readWithComments(bookId);
      if (optionalBookWithComments.isPresent()) {
        return optionalBookWithComments.get().toString();
      }
      return FAILURE_OPERATION;
    }
    return ILLEGAL_ARGUMENTS;
  }

  @Override
  @ShellMethod(value = "Update book title command. Format input: bookId ", key = {"ubt", "updateBookTitle"})
  public String updateBookTitle(long bookId) {
    if (bookId > 0) {
      Optional<Book> optionalBook = crudBookService.read(bookId);
      if (optionalBook.isPresent()) {
        Book book = optionalBook.get();
        book.setTitle(null);
        processingBookInteractiveStyle(book);
        if (crudBookService.update(book) != null) {
          return SUCCESS_OPERATION;
        }
      }
      return FAILURE_OPERATION;
    }
    return ILLEGAL_ARGUMENTS;
  }

  @Override
  @ShellMethod(value = "Update book title command. Format input: bookId", key = {"ubd", "updateBookDate"})
  public String updateBookDate(long bookId) {
    if (bookId > 0) {
      Optional<Book> optionalBook = crudBookService.read(bookId);
      if (optionalBook.isPresent()) {
        Book book = optionalBook.get();
        book.setDate(null);
        processingBookInteractiveStyle(book);
        if (crudBookService.update(book) != null) {
          return SUCCESS_OPERATION;
        }
      }
      return FAILURE_OPERATION;
    }
    return ILLEGAL_ARGUMENTS;
  }

  @Override
  @ShellMethod(value = "Update book genre command. Format input: bookId", key = {"ubg", "updateBookGenre"})
  public String updateBookGenre(long bookId) {
    if (bookId > 0) {
      Optional<Book> optionalBook = crudBookService.read(bookId);
      if (optionalBook.isPresent()) {
        Book book = optionalBook.get();
        book.setGenre(null);
        processingBookInteractiveStyle(book);
        if (crudBookService.update(book) != null) {
          return SUCCESS_OPERATION;
        }
      }
      return FAILURE_OPERATION;
    }
    return ILLEGAL_ARGUMENTS;
  }

  @Override
  @ShellMethod(value = "Update book genre command. Format input: bookId, genreId ", key = {"uba", "updateBookAuthor"})
  public String updateBookAuthor(long bookId) {
    if (bookId > 0) {
      Optional<Book> optionalBook = crudBookService.read(bookId);
      if (optionalBook.isPresent()) {
        Book book = optionalBook.get();
        book.setAuthor(null);
        processingBookInteractiveStyle(book);
        if (crudBookService.update(book) != null) {
          return SUCCESS_OPERATION;
        }
      }
      return FAILURE_OPERATION;
    }
    return ILLEGAL_ARGUMENTS;
  }

  @Override
  @ShellMethod(value = "Delete book command. Format input: bookId", key = {"db", "deleteBook"})
  public String deleteBook(long bookId) {
    if (bookId > 0) {
      crudBookService.delete(bookId);
      return SUCCESS_OPERATION;
    }
    return ILLEGAL_ARGUMENTS;
  }

  @Override
  @ShellMethod(value = "Create comment command. Format input: bookId, commentary", key = {"cc", "createComment"})
  public String createComment(long bookId, @NonNull String commentary) {
    if (bookId > 0 && !commentary.isEmpty()) {
      Optional<Book> optionalBook = crudBookService.read(bookId);
      if (optionalBook.isPresent()) {
        Comment comment = new Comment(0L, commentary, optionalBook.get());

        if (crudCommentService.create(comment) != null) {
          return SUCCESS_OPERATION;
        }
      }
      return FAILURE_OPERATION;
    }
    return ILLEGAL_ARGUMENTS;
  }

  @Override
  @ShellMethod(value = "Read comment command. Format input: commentId", key = {"rc", "readComment"})
  public String readComment(long commentId) {
    if (commentId > 0) {
      Optional<Comment> comment = crudCommentService.read(commentId);
      if (comment.isPresent()) {
        return comment.get().toString();
      }
      return FAILURE_OPERATION;
    }
    return ILLEGAL_ARGUMENTS;
  }

  @Override
  @ShellMethod(value = "Update comment command. Format input: commentId, commentary", key = {"uc", "updateComment"})
  public String updateComment(long commentId, String commentary) {
    if (commentId > 0) {
      Optional<Comment> commentOptional = crudCommentService.read(commentId);
      if (commentOptional.isPresent()) {
        Comment comment = commentOptional.get();
        comment.setCommentary(commentary);
        if (crudCommentService.update(comment) != null) {
          return SUCCESS_OPERATION;
        }
      }
      return FAILURE_OPERATION;
    }
    return ILLEGAL_ARGUMENTS;
  }

  @Override
  @ShellMethod(value = "Delete comment command. Format input: commentId", key = {"dc", "deleteComment"})
  public String deleteComment(long commentId) {
    if (commentId > 0) {
      crudCommentService.delete(commentId);
      return SUCCESS_OPERATION;
    }
    return ILLEGAL_ARGUMENTS;
  }

  @Override
  @ShellMethod(value = "Print table books command", key = {"pb", "printBooks"})
  public void printTableBooks() {
    viewRepositoryService.printTableBooks();
  }

  @Override
  @ShellMethod(value = "Print table authors command", key = {"pa", "printAuthors"})
  public void printTableAuthors() {
    viewRepositoryService.printTableAuthors();
  }

  @Override
  @ShellMethod(value = "Print table genres command", key = {"pg", "printGenres"})
  public void printTableGenres() {
    viewRepositoryService.printTableGenres();
  }

  @Override
  @ShellMethod(value = "Print table comments command", key = {"pc", "printComments"})
  public void printTableComments() {
    viewRepositoryService.printTableComments();
  }

  @SneakyThrows
  private Date convertStringToDate(String date) {
    return dateFormat.parse(date);
  }

  private Book processingBookInteractiveStyle(Book book) {
    int actualTryInput = 0;
    int tryInput = 3;
    do {
      try {
        if (book.getTitle() == null) {
          consolePrintService.printlnMessage("Please input title book");
          String data = inputReaderService.readLine();
          if (!data.isEmpty()) {
            book.setTitle(data);
          }
        }
        if (book.getDate() == null) {
          consolePrintService.printlnMessage("Please input date of issue book. format: " + dateFormat.toPattern());
          String date = inputReaderService.readLine();
          book.setDate(convertStringToDate(date));
        }
        if (book.getGenre() == null) {
          consolePrintService.printlnMessage("Please input id exist genre of book.");
          String date = inputReaderService.readToken();
          crudGenreService.read(Long.parseLong(date)).ifPresent(book::setGenre);
        }
        if (book.getAuthor() == null) {
          consolePrintService.printlnMessage("Please input id exist author of book.");
          String date = inputReaderService.readToken();
          crudAuthorService.read(Long.parseLong(date)).ifPresent(book::setAuthor);
        }
      } catch (Exception e) {
        consolePrintService.printlnMessage(e.getMessage());
        ++actualTryInput;
      }
    } while (!isValidBook(book) && actualTryInput < tryInput - 1);
    return book;
  }

  private boolean isValidBook(Book book) {
    return (book.getAuthor() != null && book.getGenre() != null && !book.getTitle().isEmpty() && book.getDate() != null);
  }

}
