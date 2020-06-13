package ru.otus.hw03.core.service;

import ru.otus.hw03.core.domain.Answer;
import ru.otus.hw03.core.domain.Question;

import java.util.List;

public interface TestValidatorService {
  int getMarkForQuestion(List<Answer> answer);
}
