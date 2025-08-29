package com.project.crud.services;

import com.project.crud.dtos.AuthorsDto;
import com.project.crud.dtos.BooksAuthorsDto;
import com.project.crud.mappers.BooksAuthorsMapper;
import com.project.crud.repositories.BooksAuthorsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BooksAuthorsService {
    private final BooksAuthorsRepository booksAuthorsRepository;
    private final BooksAuthorsMapper booksAuthorsMapper;

    public BooksAuthorsService(BooksAuthorsRepository booksAuthorsRepository, BooksAuthorsMapper booksAuthorsMapper) {
        this.booksAuthorsRepository = booksAuthorsRepository;
        this.booksAuthorsMapper = booksAuthorsMapper;
    }

    public List<BooksAuthorsDto> getAllBooksAuthors(){
        return StreamSupport.stream(booksAuthorsRepository.findAll().spliterator(), false).
                map(booksAuthorsMapper::toDto).collect(Collectors.toList());
    }

    public List<BooksAuthorsDto> getBooksAuthorsByAuthor(Integer id){
        return booksAuthorsRepository.findByIdAuthorId(id).stream().
                map(booksAuthorsMapper::toDto).collect(Collectors.toList());
    }

    public List<BooksAuthorsDto> getBooksAuthorsByBook(String isbn){
        return booksAuthorsRepository.findByIdIsbn(isbn).stream().
                map(booksAuthorsMapper::toDto).collect(Collectors.toList());
    }
}
