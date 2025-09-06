package com.project.crud.services;

import com.project.crud.dtos.ReviewsDto;
import com.project.crud.entities.embeddable.ReviewsId;
import com.project.crud.mappers.ReviewsMapper;
import com.project.crud.repositories.ReviewsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ReviewsService {
    private final ReviewsRepository reviewsRepository;
    private final ReviewsMapper reviewsMapper;

    public ReviewsService(ReviewsRepository reviewsRepository, ReviewsMapper reviewsMapper) {
        this.reviewsRepository = reviewsRepository;
        this.reviewsMapper = reviewsMapper;
    }

    public List<ReviewsDto> getAllReviews(){
        return StreamSupport.stream(reviewsRepository.findAll().spliterator(), false).
                map(reviewsMapper::toDto).collect(Collectors.toList());
    }

    public List<ReviewsDto> getAllReviewsUsername(String username){
        return reviewsRepository.findByIdUsername(username).stream().
                map(reviewsMapper::toDto).collect(Collectors.toList());
    }

    public List<ReviewsDto> getAllReviewsIsbn(String isbn){
        return reviewsRepository.findByIdIsbn(isbn).stream().
                map(reviewsMapper::toDto).collect(Collectors.toList());
    }

    public ReviewsDto getReview(ReviewsId id){
        return reviewsMapper.toDto(reviewsRepository.findById(id).orElse(null));
    }

    public HttpStatus deleteReview(String username, String isbn){
        ReviewsId embId = new ReviewsId(username, isbn);
        if (reviewsRepository.findById(embId).isEmpty()){
            return HttpStatus.NOT_FOUND;
        }
        reviewsRepository.deleteById(embId);
        return HttpStatus.OK;
    }
}
