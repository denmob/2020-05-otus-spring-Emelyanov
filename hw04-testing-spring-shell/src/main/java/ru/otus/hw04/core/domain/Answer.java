package ru.otus.hw04.core.domain;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Answer {

  private final @NonNull String titleQuestion;
  private final @NonNull Integer answerOption;

}
