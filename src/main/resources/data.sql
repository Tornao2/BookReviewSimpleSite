Insert Into Users (username, password, readBooksNumber, isAdmin) Values
    ('Tornao', 'Password', 2, false),
    ('Liki', 'Example', 3, false),
    ('Loki', '1234', 0, true);

Insert into books values
('9781784871710', 'The Picture of Dorian Gray', 'Penguin Random House', 224, 'English', 2016,
'A young man, Dorian Gray, remains eternally youthful while a hidden portrait of him ages and reflects his sins, leading to his downfall.'),
('9781784871635', 'Frankenstein', 'Penguin Random House', 232, 'English', 2016,
'A scientist, Victor Frankenstein, creates a living creature from dead bodies, but the abandoned monster turns vengeful, bringing tragedy to both their lives.'),
('9780140439908', 'De Profundis and Other Writings', 'Penguin Books Ltd', 256, 'English', 2013,
'A collection of Oscar Wilde’s reflections, including his long prison letter De Profundis, where he writes about suffering, love, art, and spiritual growth.');

Insert into reviews (UserID, ISBN, stars, description, creationDate) values
(1, '9781784871710', 3, 'Pretty average', '2025-08-27 14:30:00'),
(1, '9780140439908', 2, 'Could barely finish', '2025-08-27 15:30:00'),
(2, '9781784871710', 4, 'Bit better than average', '2025-08-27 16:30:00'),
(2, '9781784871635', 5, 'Was great', '2025-08-27 17:30:00'),
(2, '9780140439908', 5, 'Great book', '2025-08-27 18:30:00');

Insert into genres (title) values
('Letters'),
('Poetry'),
('Horror'),
('Fiction'),
('Science-Fiction');

Insert into authors (name, yearOfBirth, countryOfBirth) values
('Mary Wollstonecraft Shelley', 1797, 'United Kingdom'),
('Oscar Wilde', 1854, 'Ireland');

Insert into BooksAuthors values
('9781784871710', 2),
('9781784871635', 1),
('9780140439908', 2);

Insert into BooksGenres values
('9781784871710', 3),
('9781784871635', 4),
('9781784871635', 3),
('9781784871635', 5),
('9780140439908', 1),
('9780140439908', 2);
