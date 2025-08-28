package com.project.crud.services;

import com.project.crud.dtos.ReviewsDto;
import com.project.crud.mappers.ReviewsMapper;
import com.project.crud.repositories.ReviewsRepository;
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
}
