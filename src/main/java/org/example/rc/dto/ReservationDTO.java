package org.example.rc.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;
import java.util.Date;

@Data
public class ReservationDTO {

    @NotNull
    private Long carId;

    @NotNull
    private Integer customerId;

    @NotNull
    private String rentalPackageCode;

    @NotNull
    private Date reservationDate;

    @NotNull
    private Date rentalStartDate;

    @NotNull
    private Date rentalEndDate;
}
