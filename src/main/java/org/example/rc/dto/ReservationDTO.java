package org.example.rc.dto;

import javax.validation.constraints.NotNull;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

public class ReservationDTO extends RepresentationModel {

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

    public ReservationDTO() {
    }

    public ReservationDTO(Long carId, Integer customerId, String rentalPackageCode,
                          Date reservationDate, Date rentalStartDate, Date rentalEndDate) {
        this.carId = carId;
        this.customerId = customerId;
        this.rentalPackageCode = rentalPackageCode;
        this.reservationDate = reservationDate;
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getRentalPackageCode() {
        return rentalPackageCode;
    }

    public void setRentalPackageCode(String rentalPackageCode) {
        this.rentalPackageCode = rentalPackageCode;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Date getRentalStartDate() {
        return rentalStartDate;
    }

    public void setRentalStartDate(Date rentalStartDate) {
        this.rentalStartDate = rentalStartDate;
    }

    public Date getRentalEndDate() {
        return rentalEndDate;
    }

    public void setRentalEndDate(Date rentalEndDate) {
        this.rentalEndDate = rentalEndDate;
    }
}
