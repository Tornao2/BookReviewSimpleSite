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
    private final ReviewsRepository ReviewsRepository;
    private final ReviewsMapper ReviewsMapper;

    public ReviewsService(ReviewsRepository ReviewsReviewsRepository, ReviewsMapper ReviewsReviewsMapper) {
        this.ReviewsRepository = ReviewsReviewsRepository;
        this.ReviewsMapper = ReviewsReviewsMapper;
    }

    public List<ReviewsDto> getAllReviewsAuthors(){
        return StreamSupport.stream(ReviewsRepository.findAll().spliterator(), false).
                map(ReviewsMapper::toDto).collect(Collectors.toList());
    }
}
