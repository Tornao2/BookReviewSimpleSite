package com.project.crud.mappers;

import com.project.crud.dtos.ReviewsDto;
import com.project.crud.backend.entities.Reviews;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewsMapper {
    @Mapping(source = "id.isbn", target = "isbn")
    @Mapping(source = "id.username", target = "username")
    ReviewsDto toDto(Reviews reviews);
    @Mapping(source = "isbn", target = "id.isbn")
    @Mapping(source = "username", target = "id.username")
    Reviews toEntity(ReviewsDto reviewsDto);
}
