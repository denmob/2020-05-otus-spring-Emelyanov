package ru.otus.hw14.test.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import ru.otus.hw14.model.document.GenreDocument;

@ChangeLog(order = "002")
public class GenreChangelog {

  @ChangeSet(order = "000", id = "dropGenres", author = "dyemelianov", runAlways = true)
  public void dropGenres(MongockTemplate template) {
    template.dropCollection("genres");
  }

  @ChangeSet(order = "001", id = "addGenre01", author = "dyemelianov", runAlways = true)
  public void addGenre01(MongockTemplate template) {
    var genre = GenreDocument.builder().name("Programming(test)").build();
    template.save(genre);
  }

  @ChangeSet(order = "002", id = "addGenre02", author = "dyemelianov", runAlways = true)
  public void addGenre02(MongockTemplate template) {
    var genre = GenreDocument.builder().name("Science(test)").build();
    template.save(genre);
  }

  @ChangeSet(order = "003", id = "addGenre03", author = "dyemelianov", runAlways = true)
  public void addGenre03(MongockTemplate template) {
    var genre = GenreDocument.builder().name("Software(test)").build();
    template.save(genre);
  }
}
