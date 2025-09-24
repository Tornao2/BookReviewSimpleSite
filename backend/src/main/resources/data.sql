Insert Into Users (username, password, isAdmin) Values
    ('Tornao', 'Password', false),
    ('Liki', 'Example1', false),
    ('Loki', '12345678', true);

Insert into books values
('9781784871710', 'The Picture of Dorian Gray', 'Penguin Random House', 224, 'English', 2016,
'A young man, Dorian Gray, remains eternally youthful while a hidden portrait of him ages and reflects his sins, leading to his downfall.'),
('9781784871635', 'Frankenstein', 'Penguin Random House', 232, 'English', 2016,
'A scientist, Victor Frankenstein, creates a living creature from dead bodies, but the abandoned monster turns vengeful, bringing tragedy to both their lives.'),
('9780140439908', 'De Profundis and Other Writings', 'Penguin Books Ltd', 256, 'English', 2013,
'A collection of Oscar Wilde’s reflections, including his long prison letter De Profundis, where he writes about suffering, love, art, and spiritual growth.');

Insert into reviews (username, isbn, description, stars, changeDate) values
('Tornao', '9781784871710','Pretty average',3, '2025-08-27 14:30:00'),
('Tornao', '9780140439908','Could barely finish',2,'2025-08-27 15:30:00'),
('Liki', '9781784871710','Bit better than average',4,'2025-08-27 16:30:00'),
('Liki', '9781784871635','Was great',5,'2025-08-27 17:30:00'),
('Liki', '9780140439908','Great book',5,'2025-08-27 18:30:00');

Insert into genres values
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
('9781784871710', 'Horror'),
('9781784871635', 'Fiction'),
('9781784871635', 'Horror'),
('9781784871635', 'Science-Fiction'),
('9780140439908', 'Letters'),
('9780140439908', 'Poetry');
