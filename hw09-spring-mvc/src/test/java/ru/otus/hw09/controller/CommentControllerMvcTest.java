package ru.otus.hw09.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.otus.hw09.model.Book;
import ru.otus.hw09.model.Comment;
import ru.otus.hw09.service.CommentServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {CommentServiceImpl.class, CommentController.class})
@AutoConfigureWebMvc
@AutoConfigureMockMvc
class CommentControllerMvcTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CommentServiceImpl commentService;

  @Test
  @SneakyThrows
  @DisplayName("correct invoke(200) viewComment")
  void viewCommentPage() {
    Book book = Book.builder().id("123").title("title").build();
    List<Comment> comments = new ArrayList<>();
    comments.add(Comment.builder().commentary("comment1").build());

    when(commentService.readAllForBook(book.getId())).thenReturn(comments);

    mockMvc.perform(MockMvcRequestBuilders.get("/viewComment")
        .param("id", book.getId())
        .param("title", book.getTitle()))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().string(containsString("comment1")));
  }

  @Test
  @SneakyThrows
  @DisplayName("Required parameter is not present")
  void viewCommentPage400() {
    Book book = Book.builder().id("123").title("title").build();
    mockMvc.perform(MockMvcRequestBuilders.get("/viewComment")
        .param("id", book.getId()))
        .andExpect(status().is(400));
  }

  @Test
  @SneakyThrows
  @DisplayName("incorrect url invoke(404) viewComment")
  void viewCommentPage404() {
    mockMvc.perform(MockMvcRequestBuilders.get("/viewComment123"))
        .andExpect(status().is(404));
  }
}
