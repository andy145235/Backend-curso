package com.ucb.plataforma.res.reviews.mapper;

import com.ucb.plataforma.res.reviews.dto.ReviewCreateRequest;
import com.ucb.plataforma.res.reviews.dto.ReviewResponse;
import com.ucb.plataforma.res.reviews.dto.ReviewUpdateRequest;
import com.ucb.plataforma.res.reviews.entity.Review;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-30T13:41:44-0400",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23-valhalla (Oracle Corporation)"
)
@Component
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public ReviewResponse toResponse(Review review) {
        if ( review == null ) {
            return null;
        }

        ReviewResponse reviewResponse = new ReviewResponse();

        reviewResponse.setId( review.getId() );
        reviewResponse.setCourseId( review.getCourseId() );
        reviewResponse.setUserId( review.getUserId() );
        reviewResponse.setRating( review.getRating() );
        reviewResponse.setTitle( review.getTitle() );
        reviewResponse.setComment( review.getComment() );
        if ( review.getCreatedAt() != null ) {
            reviewResponse.setCreatedAt( review.getCreatedAt().toString() );
        }
        if ( review.getUpdatedAt() != null ) {
            reviewResponse.setUpdatedAt( review.getUpdatedAt().toString() );
        }

        return reviewResponse;
    }

    @Override
    public Review toEntity(ReviewCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        Review review = new Review();

        review.setCourseId( request.getCourseId() );
        review.setUserId( request.getUserId() );
        review.setRating( request.getRating() );
        review.setTitle( request.getTitle() );
        review.setComment( request.getComment() );

        return review;
    }

    @Override
    public void updateEntityFromDto(ReviewUpdateRequest dto, Review entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getRating() != null ) {
            entity.setRating( dto.getRating() );
        }
        if ( dto.getTitle() != null ) {
            entity.setTitle( dto.getTitle() );
        }
        if ( dto.getComment() != null ) {
            entity.setComment( dto.getComment() );
        }
    }
}
