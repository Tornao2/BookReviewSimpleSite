package com.project.crud.backend.entities;

import com.project.crud.backend.entities.embeddable.ReviewsId;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class Reviews {
    @EmbeddedId
    private ReviewsId id;
    private String description;
    private Integer stars;
    private Date changeDate;
}
