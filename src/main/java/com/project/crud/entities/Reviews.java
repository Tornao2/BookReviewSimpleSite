package com.project.crud.entities;

import com.project.crud.entities.embeddable.ReviewsId;
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
