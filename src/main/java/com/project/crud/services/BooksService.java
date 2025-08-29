package com.project.crud.services;

import com.project.crud.dtos.AuthorsDto;
import com.project.crud.dtos.BooksDto;
import com.project.crud.mappers.BooksMapper;
import com.project.crud.repositories.BooksRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BooksService {
    private final BooksRepository booksRepository;
    private final BooksMapper booksMapper;

    public BooksService(BooksRepository booksGenresRepository, BooksMapper booksGenresMapper) {
        this.booksRepository = booksGenresRepository;
        this.booksMapper = booksGenresMapper;
    }

    public List<BooksDto> getAllBooks(){
        return StreamSupport.stream(booksRepository.findAll().spliterator(), false).
                map(booksMapper::toDto).collect(Collectors.toList());
    }

    public BooksDto getBook(String isbn){
        return booksMapper.toDto(booksRepository.findById(isbn).orElse(null));
    }
}
