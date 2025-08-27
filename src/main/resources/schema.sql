DROP TABLE IF EXISTS BooksGenres CASCADE;
DROP TABLE IF EXISTS BooksAuthors CASCADE;
DROP TABLE IF EXISTS Reviews CASCADE;
DROP TABLE IF EXISTS Books CASCADE;
DROP TABLE IF EXISTS Users CASCADE;
DROP TABLE IF EXISTS Genres CASCADE;
DROP TABLE IF EXISTS Authors CASCADE;

CREATE TABLE Users (
  UserID INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  username text,
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
  ReviewID bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  UserID INTEGER,
  ISBN varchar(13),
  stars smallint,
  description text,
  creationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (ISBN) REFERENCES Books(ISBN),
  FOREIGN KEY (UserID)  REFERENCES Users(UserID)
);

CREATE TABLE Genres (
  GenreID INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  title text
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
  GenreID INTEGER,
  PRIMARY KEY (ISBN, GenreID),
  FOREIGN KEY (ISBN) REFERENCES Books(ISBN),
  FOREIGN KEY (GenreID)  REFERENCES Genres(GenreID)
);