DROP TABLE IF EXISTS BooksGenres CASCADE;
DROP TABLE IF EXISTS BooksAuthors CASCADE;
DROP TABLE IF EXISTS Reviews CASCADE;
DROP TABLE IF EXISTS Books CASCADE;
DROP TABLE IF EXISTS Users CASCADE;
DROP TABLE IF EXISTS Genres CASCADE;
DROP TABLE IF EXISTS Authors CASCADE;

CREATE TABLE Users (
  username text PRIMARY KEY,
  password text,
  readBooksNumber smallint,
  isAdmin boolean
);

CREATE TABLE Books (
  ISBN varchar(13) PRIMARY KEY,
  bookTitle text,
  publisherName text,
  pageNumber smallint,
  language text,
  yearOfRelease smallint,
  description text
);

CREATE TABLE Reviews (
  username text,
  ISBN varchar(13),
  description text,
  stars smallint,
  changeDate TIMESTAMP,
  PRIMARY KEY(username, ISBN),
  FOREIGN KEY (ISBN) REFERENCES Books(ISBN),
  FOREIGN KEY (username)  REFERENCES Users(username)
);

CREATE TABLE Genres (
  title text PRIMARY KEY
);

CREATE TABLE Authors (
  AuthorID INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  name text,
  yearOfBirth smallint,
  countryOfBirth text
);

CREATE TABLE BooksAuthors (
  ISBN varchar(13),
  AuthorID INTEGER,
  PRIMARY KEY (ISBN, AuthorID),
  FOREIGN KEY (ISBN) REFERENCES Books(ISBN),
  FOREIGN KEY (AuthorID)  REFERENCES Authors(AuthorID)
);

CREATE TABLE BooksGenres(
  ISBN varchar(13),
  title text,
  PRIMARY KEY (ISBN, title),
  FOREIGN KEY (ISBN) REFERENCES Books(ISBN),
  FOREIGN KEY (title)  REFERENCES Genres(title)
);