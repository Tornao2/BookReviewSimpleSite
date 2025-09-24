package com.project.crud.services;

import com.project.crud.dtos.BooksAuthorsDto;
import com.project.crud.backend.entities.BooksAuthors;
import com.project.crud.backend.entities.embeddable.BooksAuthorsId;
import com.project.crud.exceptionHandling.ForeignKeyNotFoundException;
import com.project.crud.exceptionHandling.ResourceAlreadyExistsException;
import com.project.crud.exceptionHandling.ResourceNotFoundException;
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
            throw new ResourceNotFoundException("BooksAuthors", "isbn: " + isbn + ", id: " + id.toString());
        }
        booksAuthorsRepository.deleteById(embId);
        return HttpStatus.OK;
    }

    public ResponseEntity<BooksAuthorsDto> postBooksAuthors(BooksAuthorsDto body){
        if(booksRepository.findById(body.getIsbn()).isEmpty()){
            throw new ForeignKeyNotFoundException("BooksAuthors", body.getIsbn(), "books");
        }
        if(authorsRepository.findById(body.getAuthorId()).isEmpty()){
            throw new ForeignKeyNotFoundException("BooksAuthors", body.getAuthorId().toString(), "authors");
        }
        BooksAuthorsId searched = new BooksAuthorsId(body.getIsbn(), body.getAuthorId());
        if(booksAuthorsRepository.findById(searched).isPresent()){
            throw new ResourceAlreadyExistsException("BooksAuthors", "isbn: " + body.getIsbn() + ", authorid: " + body.getAuthorId());
        }
        BooksAuthors entity = booksAuthorsMapper.toEntity(body);
        BooksAuthors saved = booksAuthorsRepository.save(entity);
        BooksAuthorsDto dto = booksAuthorsMapper.toDto(saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}
