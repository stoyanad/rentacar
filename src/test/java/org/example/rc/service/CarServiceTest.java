package org.example.rc.service;

import org.example.rc.domain.Car;
import org.example.rc.dto.CarDTO;
import org.example.rc.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    private Car car;
    private CarDTO carDTO;

    @BeforeEach
    public void setup() {
        car = new Car();
        car.setId(1L);
        car.setMake("Toyota");
        car.setModel("Corolla");
        car.setYear(2020);
        car.setColor("Blue");
        car.setLicensePlate("ABC-123");
        car.setPricePerDay(100.0);
        car.setPricePerWeek(600.0);
        car.setPricePerMonth(2000.0);
        car.setPricePerYear(22000.0);
        car.setAvailability(true);
        car.setKeywords(String.valueOf(Arrays.asList("economy", "sedan")));

        carDTO = new CarDTO();
        carDTO.setId(1L);
        carDTO.setMake("Toyota");
        carDTO.setModel("Corolla");
        carDTO.setYear(2020);
        carDTO.setColor("Blue");
        carDTO.setLicensePlate("ABC-123");
        carDTO.setPricePerDay(100.0);
        carDTO.setPricePerWeek(600.0);
        carDTO.setPricePerMonth(2000.0);
        carDTO.setPricePerYear(22000.0);
        carDTO.setAvailability(true);
        carDTO.setKeywords(String.valueOf(Arrays.asList("economy", "sedan")));

    }

    @Test
    void testFindAll() {
        when(carRepository.findAll()).thenReturn(Collections.singletonList(car));

        List<CarDTO> cars = carService.findAll();

        assertThat(cars.size(), is(1));
        assertThat(cars.get(0), is(carDTO));
        verify(carRepository).findAll();
    }

    @Test
    void testFindById() {
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));

        Optional<CarDTO> foundCar = carService.findById(1L);

        assertThat(foundCar.isPresent(), is(true));
        assertThat(foundCar.get(), is(carDTO));
        verify(carRepository).findById(1L);
    }

    @Test
    void testSave() {
        when(carRepository.save(any(Car.class))).thenReturn(car);

        CarDTO savedCarDTO = carService.save(carDTO);

        assertThat(savedCarDTO, is(carDTO));
        verify(carRepository).save(any(Car.class));
    }

    @Test
    void testDeleteById() {
        doNothing().when(carRepository).deleteById(1L);

        carService.deleteById(1L);

        verify(carRepository).deleteById(1L);
    }
}
