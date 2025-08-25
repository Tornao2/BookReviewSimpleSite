CREATE TABLE Users (
  UserID INTEGER PRIMARY KEY,
  username text,
  loginName text,
  password text,
  email text,
  readBooksNumber smallint,
  isAdmin boolean
);

CREATE TABLE Books (
  ISBN varchar(13) PRIMARY KEY,
  bookTitle text,
  publisherName text,
  pageNumber smallint,
  language text,
  description text,
  year smallint
);

CREATE TABLE Reviews (
  ReviewID bigint PRIMARY KEY,
  UserID INTEGER,
  ISBN varchar(13),
  description text,
  stars smallint,
  createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (ISBN) REFERENCES Books(ISBN),
  FOREIGN KEY (UserID)  REFERENCES Users(UserID)
);

CREATE TABLE Genres (
  GenreID INTEGER PRIMARY KEY,
  title text
);

CREATE TABLE Authors (
  AuthorID INTEGER PRIMARY KEY,
  name text,
  yearOfBirth text,
  countryOfBirth text
);

CREATE TABLE BookAuthor (
  ISBN varchar(13),
  AuthorID INTEGER,
  PRIMARY KEY (ISBN, AuthorID),
  FOREIGN KEY (ISBN) REFERENCES Books(ISBN),
  FOREIGN KEY (AuthorID)  REFERENCES Authors(AuthorID)
);

CREATE TABLE BookGenre(
  ISBN varchar(13),
  GenreID INTEGER,
  PRIMARY KEY (ISBN, GenreID),
  FOREIGN KEY (ISBN) REFERENCES Books(ISBN),
  FOREIGN KEY (GenreID)  REFERENCES Genres(GenreID)
);