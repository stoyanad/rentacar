package org.example.rc.service;

import org.example.rc.domain.Car;
import org.example.rc.dto.CarDTO;
import org.example.rc.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarDTO> findAll() {
        return carRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<CarDTO> findById(Long id) {
        return carRepository.findById(id).map(this::convertToDTO);
    }

    public CarDTO save(CarDTO carDTO) {
        Car car = convertToEntity(carDTO);
        return convertToDTO(carRepository.save(car));
    }

    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    private CarDTO convertToDTO(Car car) {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(car.getId());
        carDTO.setMake(car.getMake());
        carDTO.setModel(car.getModel());
        carDTO.setYear(car.getYear());
        carDTO.setColor(car.getColor());
        carDTO.setLicensePlate(car.getLicensePlate());
        carDTO.setPricePerDay(car.getPricePerDay());
        carDTO.setPricePerWeek(car.getPricePerWeek());
        carDTO.setPricePerMonth(car.getPricePerMonth());
        carDTO.setPricePerYear(car.getPricePerYear());
        carDTO.setAvailability(car.isAvailability());
        carDTO.setKeywords(car.getKeywords());
        return carDTO;
    }

    private Car convertToEntity(CarDTO carDTO) {
        Car car = new Car();
        car.setId(carDTO.getId());
        car.setMake(carDTO.getMake());
        car.setModel(carDTO.getModel());
        car.setYear(carDTO.getYear());
        car.setColor(carDTO.getColor());
        car.setLicensePlate(carDTO.getLicensePlate());
        car.setPricePerDay(carDTO.getPricePerDay());
        car.setPricePerWeek(carDTO.getPricePerWeek());
        car.setPricePerMonth(carDTO.getPricePerMonth());
        car.setPricePerYear(carDTO.getPricePerYear());
        car.setAvailability(carDTO.isAvailability());
        car.setKeywords(carDTO.getKeywords());
        return car;
    }
}
