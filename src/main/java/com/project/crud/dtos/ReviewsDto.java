package com.project.crud.dtos;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class ReviewsDto {
    private String username;
    private String isbn;
    private String description;
    private Integer stars;
    private Date changeDate;
}
