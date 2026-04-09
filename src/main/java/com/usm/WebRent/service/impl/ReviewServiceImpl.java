package com.usm.WebRent.service.impl;

import com.usm.WebRent.entity.Car;
import com.usm.WebRent.entity.Rental;
import com.usm.WebRent.entity.Review;
import com.usm.WebRent.entity.Users;
import com.usm.WebRent.entity.enums.ReviewRating;
import com.usm.WebRent.exception.EmptyListException;
import com.usm.WebRent.exception.ReviewNotFoundException;
import com.usm.WebRent.exception.ReviewRatingException;
import com.usm.WebRent.exception.UserNotFoundException;
import com.usm.WebRent.repository.ReviewRepository;
import com.usm.WebRent.service.ReviewService;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public Review save(Review review) {
        // Presupunem că ReviewRating este un Enum sau un număr
        if (review.getRating() == null) {
            throw new ReviewRatingException();
        }

        // Verificăm dacă user-ul care lasă review există
        if (review.getUser() == null || review.getUser().getId() == null) {
            throw new UserNotFoundException(null);
        }

        return reviewRepository.save(review);
    }

    @Override
    public List<Review> findAll() {
        List<Review> reviews = reviewRepository.findAll();
        if (reviews.isEmpty()) {
            throw new EmptyListException("Reviews");
        }
        return reviews;
    }

    @Override
    public Review findById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));
    }

    @Override
    public Review update(Long id, Review reviewDetails) {
        Review review = findById(id);

        review.setCar(reviewDetails.getCar());
        review.setUser(reviewDetails.getUser());
        review.setRating(reviewDetails.getRating());
        review.setComment(reviewDetails.getComment());
        review.setCreatedAt(reviewDetails.getCreatedAt());

        return reviewRepository.save(review);
    }

    @Override
    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }
}
