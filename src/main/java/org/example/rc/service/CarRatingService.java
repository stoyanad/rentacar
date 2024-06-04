package org.example.rc.service;

import org.example.rc.domain.Car;
import org.example.rc.domain.CarRating;
import org.example.rc.domain.User;
import org.example.rc.dto.CarRatingDTO;
import org.example.rc.repository.CarRatingRepository;
import org.example.rc.repository.CarRepository;
import org.example.rc.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarRatingService {

    private final CarRatingRepository carRatingRepository;

    private final CarRepository carRepository;

    private final UserRepository userRepository;

    public CarRatingService(CarRatingRepository carRatingRepository, CarRepository carRepository, UserRepository userRepository) {
        this.carRatingRepository = carRatingRepository;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    public List<CarRatingDTO> findAll() {
        return carRatingRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<CarRatingDTO> findById(Long id) {
        return carRatingRepository.findById(id).map(this::convertToDTO);
    }

    public CarRatingDTO save(CarRatingDTO carRatingDTO) {
        CarRating carRating = convertToEntity(carRatingDTO);
        return convertToDTO(carRatingRepository.save(carRating));
    }

    public void deleteById(Long id) {
        carRatingRepository.deleteById(id);
    }

    private CarRatingDTO convertToDTO(CarRating carRating) {
        CarRatingDTO carRatingDTO = new CarRatingDTO();
        carRatingDTO.setId(carRating.getId());
        carRatingDTO.setCarId(carRating.getCar().getId());
        carRatingDTO.setCustomerId(carRating.getCustomer().getId());
        carRatingDTO.setScore(carRating.getScore());
        carRatingDTO.setComment(carRating.getComment());
        return carRatingDTO;
    }

    private CarRating convertToEntity(CarRatingDTO carRatingDTO) {
        CarRating carRating = new CarRating();

        Optional<Car> car = carRepository.findById(carRatingDTO.getCarId());
        Optional<User> user = userRepository.findById(carRatingDTO.getCustomerId());

        if (car.isPresent() && user.isPresent()) {
            carRating.setCar(car.get());
            carRating.setCustomer(user.get());
            carRating.setScore(carRatingDTO.getScore());
            carRating.setComment(carRatingDTO.getComment());
        }

        return carRating;
    }
}
