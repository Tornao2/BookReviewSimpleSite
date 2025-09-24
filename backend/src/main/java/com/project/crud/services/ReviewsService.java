package com.project.crud.services;

import com.project.crud.dtos.ReviewsDto;
import com.project.crud.backend.entities.Reviews;
import com.project.crud.backend.entities.embeddable.ReviewsId;
import com.project.crud.exceptionHandling.ForeignKeyNotFoundException;
import com.project.crud.exceptionHandling.ResourceAlreadyExistsException;
import com.project.crud.exceptionHandling.ResourceNotFoundException;
import com.project.crud.mappers.ReviewsMapper;
import com.project.crud.repositories.BooksRepository;
import com.project.crud.repositories.ReviewsRepository;
import com.project.crud.repositories.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ReviewsService {
    private final ReviewsRepository reviewsRepository;
    private final ReviewsMapper reviewsMapper;
    private final BooksRepository booksRepository;
    private final UsersRepository usersRepository;

    public ReviewsService(ReviewsRepository reviewsRepository, ReviewsMapper reviewsMapper, BooksRepository booksRepository, UsersRepository usersRepository) {
        this.reviewsRepository = reviewsRepository;
        this.reviewsMapper = reviewsMapper;
        this.booksRepository = booksRepository;
        this.usersRepository = usersRepository;
    }

    public List<ReviewsDto> getAllReviews(){
        return StreamSupport.stream(reviewsRepository.findAll().spliterator(), false).
                map(reviewsMapper::toDto).collect(Collectors.toList());
    }

    public List<ReviewsDto> getAllReviewsUsername(String username){
        return reviewsRepository.findByIdUsername(StringUtils.capitalize(username.toLowerCase())).stream().
                map(reviewsMapper::toDto).collect(Collectors.toList());
    }

    public List<ReviewsDto> getAllReviewsIsbn(String isbn){
        return reviewsRepository.findByIdIsbn(isbn).stream().
                map(reviewsMapper::toDto).collect(Collectors.toList());
    }

    public ReviewsDto getReview(ReviewsId id){
        return reviewsMapper.toDto(reviewsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("review", "username: " + id.getUsername() + ", isbn: " + id.getIsbn())));
    }

    public HttpStatus deleteReview(String username, String isbn){
        ReviewsId embId = new ReviewsId(username, isbn);
        if (reviewsRepository.findById(embId).isEmpty()){
            throw new ResourceNotFoundException("review", "username: " + username + " ,isbn: " + isbn);
        }
        reviewsRepository.deleteById(embId);
        return HttpStatus.OK;
    }

    public ResponseEntity<ReviewsDto> postReview(ReviewsDto body){
        if(booksRepository.findById(body.getIsbn()).isEmpty()){
            throw new ForeignKeyNotFoundException("review", body.getIsbn(), "books");
        }
        if(usersRepository.findById(body.getUsername()).isEmpty()){
            throw new ForeignKeyNotFoundException("review", body.getUsername(), "users");
        }
        ReviewsId searched = new ReviewsId(body.getUsername(), body.getIsbn());
        if(reviewsRepository.findById(searched).isPresent()){
            throw new ResourceAlreadyExistsException("review", "isbn: " + body.getIsbn() + ", username: " + body.getUsername());
        }
        Reviews entity = reviewsMapper.toEntity(body);
        entity.setChangeDate(Date.from(Instant.now()));
        Reviews saved = reviewsRepository.save(entity);
        ReviewsDto dto = reviewsMapper.toDto(saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    public ResponseEntity<ReviewsDto> updateReview(ReviewsDto body, String username, String isbn) {
        ReviewsId revId = new ReviewsId(username, isbn);
        body.setIsbn(isbn);
        body.setUsername(username);
        if (!reviewsRepository.existsById(revId)){
            throw new ResourceNotFoundException("review", "isbn: " + revId.getIsbn() + ", username: " + revId.getUsername());
        }
        body.setChangeDate(Date.from(Instant.now()));
        Reviews review = reviewsRepository.save(reviewsMapper.toEntity(body));
        return ResponseEntity.status(HttpStatus.OK).body(reviewsMapper.toDto(review));
    }
}
