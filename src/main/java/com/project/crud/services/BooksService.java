package com.project.crud.services;

import com.project.crud.dtos.BooksDto;
import com.project.crud.entities.Books;
import com.project.crud.exceptionHandling.ForeignKeyFoundException;
import com.project.crud.exceptionHandling.ResourceNotFoundException;
import com.project.crud.mappers.BooksMapper;
import com.project.crud.repositories.BooksAuthorsRepository;
import com.project.crud.repositories.BooksGenresRepository;
import com.project.crud.repositories.BooksRepository;
import com.project.crud.repositories.ReviewsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final ReviewsRepository reviewsRepository;

    public BooksService(BooksRepository booksRepository, BooksMapper booksMapper,
                        BooksAuthorsRepository booksAuthorsRepository, BooksGenresRepository booksGenresRepository, ReviewsRepository reviewsRepository) {
        this.booksRepository = booksRepository;
        this.booksMapper = booksMapper;
        this.booksAuthorsRepository = booksAuthorsRepository;
        this.booksGenresRepository = booksGenresRepository;
        this.reviewsRepository = reviewsRepository;
    }

    public List<BooksDto> getAllBooks(){
        return StreamSupport.stream(booksRepository.findAll().spliterator(), false).
                map(booksMapper::toDto).collect(Collectors.toList());
    }

    public BooksDto getBook(String isbn){
        return booksMapper.toDto(booksRepository.findById(isbn).orElseThrow(
                () -> new ResourceNotFoundException("book", isbn)));
    }

    public HttpStatus deleteBook(String isbn){
        if (!booksAuthorsRepository.findByIdIsbn(isbn).isEmpty()){
            throw new ForeignKeyFoundException("book", isbn, "BooksAuthors");
        }
        if (!booksGenresRepository.findByIdIsbn(isbn).isEmpty()) {
            throw new ForeignKeyFoundException("book", isbn, "BooksGenres");
        }
        if (!reviewsRepository.findByIdIsbn(isbn).isEmpty()) {
            throw new ForeignKeyFoundException("book", isbn, "Reviews");
        }
        if (!booksRepository.existsById(isbn)){
            throw new ResourceNotFoundException("book", isbn);
        }
        booksRepository.deleteById(isbn);
        return HttpStatus.OK;
    }

    public ResponseEntity<BooksDto> postBooks(BooksDto body) {
        if (body.getIsbn().length() != 13){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
        if(booksRepository.findById(body.getIsbn()).isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body
                    (booksMapper.toDto(booksRepository.findById(body.getIsbn()).get()));
        }
        Books entity = booksMapper.toEntity(body);
        Books saved = booksRepository.save(entity);
        BooksDto dto = booksMapper.toDto(saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}
