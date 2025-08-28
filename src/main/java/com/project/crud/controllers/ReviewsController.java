package com.project.crud.controllers;

import com.project.crud.dtos.ReviewsDto;
import com.project.crud.services.ReviewsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {
    private final ReviewsService ReviewsService;

    public ReviewsController(ReviewsService ReviewsService) {
        this.ReviewsService = ReviewsService;
    }

    @GetMapping
    public List<ReviewsDto> getAllReviews() {
        return ReviewsService.getAllReviews();
    }
}
