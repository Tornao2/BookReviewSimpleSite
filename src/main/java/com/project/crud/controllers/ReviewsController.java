package com.project.crud.controllers;

import com.project.crud.dtos.ReviewsDto;
import com.project.crud.entities.embeddable.ReviewsId;
import com.project.crud.services.ReviewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/both/{username}/{isbn}")
    public ResponseEntity<ReviewsDto> getAReview(@PathVariable String username, @PathVariable String isbn) {
        ReviewsId rev = new ReviewsId(username, isbn);
        ReviewsDto returnValue = reviewsService.getReview(rev);
        if (returnValue == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(returnValue);
    }

    @GetMapping("/username/{username}")
    public List<ReviewsDto> getReviewUsername(@PathVariable String username) {
        return reviewsService.getAllReviewsUsername(username);
    }

    @GetMapping("/isbn/{isbn}")
    public List<ReviewsDto> getReviewIsbn(@PathVariable String isbn) {
        return reviewsService.getAllReviewsIsbn(isbn);
    }

    @DeleteMapping("/{username}/{isbn}")
    public ResponseEntity<Void> deleteReview(@PathVariable String username, @PathVariable String isbn){
        return ResponseEntity.status(reviewsService.deleteReview(username, isbn)).build();
    }

    @PostMapping("/")
    public ResponseEntity<ReviewsDto> postReview(@RequestBody ReviewsDto body){
        return reviewsService.postReview(body);
    }
}
