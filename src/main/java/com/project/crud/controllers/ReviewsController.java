package com.project.crud.controllers;

import com.project.crud.dtos.ReviewsDto;
import com.project.crud.services.ReviewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {
    private final ReviewsService reviewsService;

    public ReviewsController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    @GetMapping("/")
    public List<ReviewsDto> getAllReviews() {
        return reviewsService.getAllReviews();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewsDto> getReview(@PathVariable Integer id) {
        ReviewsDto returnValue = reviewsService.getReview(id);
        if (returnValue == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(returnValue);
    }
}
