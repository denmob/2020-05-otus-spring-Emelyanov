insert into authors(id, first_name, last_name, birthday)
values (1, 'Jeff', 'Langr', '1969-11-08'),
       (2, 'Joshua', 'Bloch', '1961-08-29'),
       (3, 'Cay S.', 'Horstmann ', '1959-03-19');

insert into genres(id, name)
values (1, 'Programming'),
       (2, 'Science'),
       (3, 'Software');

insert into books(id, author_id, genre_id, title, date)
values (1, 1, 3, 'Pragmatic Unit Testing in Java 8 with JUnit', '2015-05-01'),
       (2, 2, 1, 'Effective Java', '2018-01-01'),
       (3, 3, 2, 'Java Core Fundamentals', '2016-05-17');

insert into comments(id, commentary, book_id)
values (1, 'Test 1', 1),
       (2, 'Test 2', 1);
