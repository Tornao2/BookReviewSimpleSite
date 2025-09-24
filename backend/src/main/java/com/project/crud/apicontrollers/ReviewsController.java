package com.project.crud.apicontrollers;

import com.project.crud.dtos.ReviewsDto;
import com.project.crud.backend.entities.embeddable.ReviewsId;
import com.project.crud.services.ReviewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
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
        return ResponseEntity.ok(reviewsService.getReview(rev));
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

    @PutMapping("/{username}/{isbn}")
    public ResponseEntity<ReviewsDto> updateReview(@RequestBody ReviewsDto body, @PathVariable String username, @PathVariable String isbn){
        return reviewsService.updateReview(body, username, isbn);
    }
}
