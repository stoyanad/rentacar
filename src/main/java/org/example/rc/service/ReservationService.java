package org.example.rc.service;

import org.example.rc.domain.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    List<Reservation> getAllReservations();
    Optional<Reservation> getReservationById(Long id);
    Reservation createReservation(Reservation reservation);
    void deleteReservation(Long id);
    List<Reservation> getReservationsByCustomerId(Long customerId);
    List<Reservation> getReservationsByCarId(Long carId);
}
