package ru.otus.hw14.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authors")
public class AuthorEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "first_name", nullable = false, unique = true)
  private @NonNull String firstName;

  @Column(name = "last_name", nullable = false, unique = true)
  private @NonNull String lastName;

  @Column(name = "birthday", nullable = false, unique = true)
  private @NonNull Date birthday;
}
