package com.project.crud.services;

import com.project.crud.dtos.BooksAuthorsDto;
import com.project.crud.entities.BooksAuthors;
import com.project.crud.entities.embeddable.BooksAuthorsId;
import com.project.crud.mappers.BooksAuthorsMapper;
import com.project.crud.repositories.AuthorsRepository;
import com.project.crud.repositories.BooksAuthorsRepository;
import com.project.crud.repositories.BooksRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BooksAuthorsService {
    private final BooksAuthorsRepository booksAuthorsRepository;
    private final BooksAuthorsMapper booksAuthorsMapper;
    private final BooksRepository booksRepository;
    private final AuthorsRepository authorsRepository;

    public BooksAuthorsService(BooksAuthorsRepository booksAuthorsRepository, BooksAuthorsMapper booksAuthorsMapper, BooksRepository booksRepository, AuthorsRepository authorsRepository) {
        this.booksAuthorsRepository = booksAuthorsRepository;
        this.booksAuthorsMapper = booksAuthorsMapper;
        this.booksRepository = booksRepository;
        this.authorsRepository = authorsRepository;
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

    public HttpStatus deleteBooksAuthors(String isbn, Integer id){
        BooksAuthorsId embId = new BooksAuthorsId(isbn, id);
        if (booksAuthorsRepository.findById(embId).isEmpty()){
            return HttpStatus.NOT_FOUND;
        }
        booksAuthorsRepository.deleteById(embId);
        return HttpStatus.OK;
    }

    public ResponseEntity<BooksAuthorsDto> postBooksAuthors(BooksAuthorsDto body){
        if(booksRepository.findById(body.getIsbn()).isEmpty() ||
            authorsRepository.findById(body.getAuthorId()).isEmpty()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
        BooksAuthorsId searched = new BooksAuthorsId(body.getIsbn(), body.getAuthorId());
        if(booksAuthorsRepository.findById(searched).isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
        }
        BooksAuthors entity = booksAuthorsMapper.toEntity(body);
        BooksAuthors saved = booksAuthorsRepository.save(entity);
        BooksAuthorsDto dto = booksAuthorsMapper.toDto(saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}
