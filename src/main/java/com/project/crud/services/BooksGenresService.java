package com.project.crud.services;

import com.project.crud.dtos.BooksGenresDto;
import com.project.crud.entities.embeddable.BooksGenresId;
import com.project.crud.mappers.BooksGenresMapper;
import com.project.crud.repositories.BooksGenresRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BooksGenresService {
    private final BooksGenresRepository booksGenresRepository;
    private final BooksGenresMapper booksGenresMapper;

    public BooksGenresService(BooksGenresRepository booksGenresRepository, BooksGenresMapper booksGenresMapper) {
        this.booksGenresRepository = booksGenresRepository;
        this.booksGenresMapper = booksGenresMapper;
    }

    public List<BooksGenresDto> getAllBooksGenres(){
        return StreamSupport.stream(booksGenresRepository.findAll().spliterator(), false).
                map(booksGenresMapper::toDto).collect(Collectors.toList());
    }

    public List<BooksGenresDto> getBooksGenresByGenre(String title){
        return booksGenresRepository.findByIdTitle(title).stream().
                map(booksGenresMapper::toDto).collect(Collectors.toList());
    }

    public List<BooksGenresDto> getBooksGenresByBook(String isbn){
        return booksGenresRepository.findByIdIsbn(isbn).stream().
                map(booksGenresMapper::toDto).collect(Collectors.toList());
    }

    public HttpStatus deleteBooksGenres(String isbn, String title){
        BooksGenresId embId = new BooksGenresId(isbn, title);
        if (booksGenresRepository.findById(embId).isEmpty()){
            return HttpStatus.NOT_FOUND;
        }
        booksGenresRepository.deleteById(embId);
        return HttpStatus.OK;
    }
}
