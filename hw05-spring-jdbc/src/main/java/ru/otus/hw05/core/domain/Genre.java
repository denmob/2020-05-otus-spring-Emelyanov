package ru.otus.hw05.core.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Genre {

  private long id;
  private @NonNull String name;

}
