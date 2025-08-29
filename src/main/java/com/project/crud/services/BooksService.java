package com.project.crud.services;

import com.project.crud.dtos.BooksDto;
import com.project.crud.mappers.BooksMapper;
import com.project.crud.repositories.BooksAuthorsRepository;
import com.project.crud.repositories.BooksGenresRepository;
import com.project.crud.repositories.BooksRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BooksService {
    private final BooksRepository booksRepository;
    private final BooksMapper booksMapper;
    private final BooksAuthorsRepository booksAuthorsRepository;
    private final BooksGenresRepository booksGenresRepository;

    public BooksService(BooksRepository booksRepository, BooksMapper booksMapper,
                        BooksAuthorsRepository booksAuthorsRepository, BooksGenresRepository booksGenresRepository) {
        this.booksRepository = booksRepository;
        this.booksMapper = booksMapper;
        this.booksAuthorsRepository = booksAuthorsRepository;
        this.booksGenresRepository = booksGenresRepository;
    }

    public List<BooksDto> getAllBooks(){
        return StreamSupport.stream(booksRepository.findAll().spliterator(), false).
                map(booksMapper::toDto).collect(Collectors.toList());
    }

    public BooksDto getBook(String isbn){
        return booksMapper.toDto(booksRepository.findById(isbn).orElse(null));
    }

    public HttpStatus deleteBook(String isbn){
        if (!booksAuthorsRepository.findByIdIsbn(isbn).isEmpty() || !booksGenresRepository.findByIdIsbn(isbn).isEmpty()
        ){
            return HttpStatus.CONFLICT;
        }
        if (!booksRepository.existsById(isbn)){
            return HttpStatus.NOT_FOUND;
        }
        booksRepository.deleteById(isbn);
        return HttpStatus.OK;
    }
}
