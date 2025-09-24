package com.project.crud.services;

import com.project.crud.dtos.BooksGenresDto;
import com.project.crud.backend.entities.BooksGenres;
import com.project.crud.backend.entities.embeddable.BooksGenresId;
import com.project.crud.exceptionHandling.ForeignKeyNotFoundException;
import com.project.crud.exceptionHandling.ResourceAlreadyExistsException;
import com.project.crud.exceptionHandling.ResourceNotFoundException;
import com.project.crud.mappers.BooksGenresMapper;
import com.project.crud.repositories.BooksGenresRepository;
import com.project.crud.repositories.BooksRepository;
import com.project.crud.repositories.GenresRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BooksGenresService {
    private final BooksGenresRepository booksGenresRepository;
    private final BooksGenresMapper booksGenresMapper;
    private final BooksRepository booksRepository;
    private final GenresRepository genresRepository;

    public BooksGenresService(BooksGenresRepository booksGenresRepository, BooksGenresMapper booksGenresMapper, BooksRepository booksRepository, GenresRepository genresRepository) {
        this.booksGenresRepository = booksGenresRepository;
        this.booksGenresMapper = booksGenresMapper;
        this.booksRepository = booksRepository;
        this.genresRepository = genresRepository;
    }

    public List<BooksGenresDto> getAllBooksGenres(){
        return StreamSupport.stream(booksGenresRepository.findAll().spliterator(), false).
                map(booksGenresMapper::toDto).collect(Collectors.toList());
    }

    public List<BooksGenresDto> getBooksGenresByGenre(String title){
        return booksGenresRepository.findByIdTitle(StringUtils.capitalize(title.toLowerCase())).stream().
                map(booksGenresMapper::toDto).collect(Collectors.toList());
    }

    public List<BooksGenresDto> getBooksGenresByBook(String isbn){
        return booksGenresRepository.findByIdIsbn(isbn).stream().
                map(booksGenresMapper::toDto).collect(Collectors.toList());
    }

    public HttpStatus deleteBooksGenres(String isbn, String title){
        BooksGenresId embId = new BooksGenresId(isbn, StringUtils.capitalize(title.toLowerCase()));
        if (booksGenresRepository.findById(embId).isEmpty()){
            throw new ResourceNotFoundException("BooksGenres", "isbn: " + isbn + ", title: " + title);
        }
        booksGenresRepository.deleteById(embId);
        return HttpStatus.OK;
    }

    public ResponseEntity<BooksGenresDto> postBooksGenres(BooksGenresDto body){
        body.setTitle(StringUtils.capitalize(body.getTitle().toLowerCase()));
        if(booksRepository.findById(body.getIsbn()).isEmpty()){
            throw new ForeignKeyNotFoundException("BooksGenres", body.getIsbn(), "books");
        }
        if(genresRepository.findById(body.getTitle()).isEmpty()){
            throw new ForeignKeyNotFoundException("BooksGenres", body.getTitle(), "genres");
        }
        BooksGenresId searched = new BooksGenresId(body.getIsbn(), body.getTitle());
        if(booksGenresRepository.findById(searched).isPresent()){
            throw new ResourceAlreadyExistsException("BooksGenres", "isbn: " + body.getIsbn() + ", genre: " + body.getTitle());
        }
        BooksGenres entity = booksGenresMapper.toEntity(body);
        BooksGenres saved = booksGenresRepository.save(entity);
        BooksGenresDto dto = booksGenresMapper.toDto(saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}
