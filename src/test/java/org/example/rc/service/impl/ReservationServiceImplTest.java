package org.example.rc.service.impl;

import org.example.rc.domain.Reservation;
import org.example.rc.domain.Car;
import org.example.rc.domain.User;
import org.example.rc.domain.RentalPackage;
import org.example.rc.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    private Reservation reservation;
    private Car car;
    private User customer;
    private RentalPackage rentalPackage;

    @BeforeEach
    public void setup() {
        car = new Car();
        car.setId(1L);

        customer = new User();
        customer.setId(1L);

        rentalPackage = new RentalPackage();
        rentalPackage.setCode("DA");

        reservation = new Reservation();
        reservation.setId(1L);
        reservation.setCar(car);
        reservation.setCustomer(customer);
        reservation.setRentalPackage(rentalPackage);
        reservation.setReservationDate(new Date());
        reservation.setRentalStartDate(new Date());
        reservation.setRentalEndDate(new Date());

    }

    @Test
    void getAllReservations() {
        when(reservationRepository.findAll()).thenReturn(Collections.singletonList(reservation));

        List<Reservation> reservations = reservationService.getAllReservations();

        verify(reservationRepository).findAll();
        assertThat(Collections.singletonList(reservation), is(reservations));
    }

    @Test
    void getReservationById() {
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        Optional<Reservation> foundReservation = reservationService.getReservationById(1L);

        assertThat(foundReservation.isPresent(), is(true));
        assertThat(foundReservation.get(), is(reservation));
        verify(reservationRepository).findById(1L);
    }

    @Test
    void createReservation() {
        Reservation newReservation = new Reservation();
        newReservation.setCar(car);
        newReservation.setCustomer(customer);
        newReservation.setRentalPackage(rentalPackage);
        newReservation.setReservationDate(new Date());
        newReservation.setRentalStartDate(new Date());
        newReservation.setRentalEndDate(new Date());
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        reservationService.createReservation(newReservation);

        verify(reservationRepository).save(newReservation);
    }

    @Test
    void deleteReservation() {
        reservationService.deleteReservation(1L);

        verify(reservationRepository).deleteById(1L);
    }

    @Test
    void getReservationsByCustomerId() {
        when(reservationRepository.findByCustomerId(1L)).thenReturn(Collections.singletonList(reservation));

        List<Reservation> reservations = reservationService.getReservationsByCustomerId(1L);

        assertThat(reservations.size(), is(1));
        assertThat(reservations.get(0), is(reservation));
        verify(reservationRepository).findByCustomerId(1L);
    }

    @Test
    void getReservationsByCarId() {
        when(reservationRepository.findByCarId(1L)).thenReturn(Collections.singletonList(reservation));

        List<Reservation> reservations = reservationService.getReservationsByCarId(1L);

        assertThat(reservations.size(), is(1));
        assertThat(reservations.get(0), is(reservation));
        verify(reservationRepository).findByCarId(1L);
    }
}
