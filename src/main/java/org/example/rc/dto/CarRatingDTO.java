package org.example.rc.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Data Transfer Object for Rating a Car.
 */
public class CarRatingDTO {
    @NotNull
    private Long id;
    @NotNull
    private Long carId;

    @Min(0)
    @Max(5)
    private Integer score;

    @Size(max = 255)
    private String comment;

    @NotNull
    private Long customerId;

    public @NotNull Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(@NotNull Long customerId) {
        this.customerId = customerId;
    }

    public @Size(max = 255) String getComment() {
        return comment;
    }

    public void setComment(@Size(max = 255) String comment) {
        this.comment = comment;
    }

    public @Min(0) @Max(5) Integer getScore() {
        return score;
    }

    public void setScore(@Min(0) @Max(5) Integer score) {
        this.score = score;
    }

    public @NotNull Long getCarId() {
        return carId;
    }

    public void setCarId(@NotNull Long carId) {
        this.carId = carId;
    }

    public @NotNull Long getId() {
        return id;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }
}
