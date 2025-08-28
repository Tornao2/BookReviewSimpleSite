package com.project.crud.mappers;

import com.project.crud.dtos.ReviewsDto;
import com.project.crud.entities.Reviews;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewsMapper {
    ReviewsDto toDto(Reviews reviews);
    Reviews toEntity(ReviewsDto reviewsDto);
}
