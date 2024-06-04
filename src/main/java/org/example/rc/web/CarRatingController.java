package org.example.rc.web;

import org.example.rc.dto.CarRatingDTO;
import org.example.rc.service.CarRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car-ratings")
public class CarRatingController {

    @Autowired
    private CarRatingService carRatingService;

    @GetMapping
    public List<CarRatingDTO> getAllCarRatings() {
        return carRatingService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarRatingDTO> getCarRatingById(@PathVariable Long id) {
        return carRatingService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public CarRatingDTO createCarRating(@RequestBody CarRatingDTO carRatingDTO) {
        return carRatingService.save(carRatingDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarRatingDTO> updateCarRating(@PathVariable Long id, @RequestBody CarRatingDTO carRatingDTO) {
        return carRatingService.findById(id)
                .map(existingRating -> {
                    carRatingDTO.setId(id);
                    return ResponseEntity.ok(carRatingService.save(carRatingDTO));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarRating(@PathVariable Long id) {
        if (carRatingService.findById(id).isPresent()) {
            carRatingService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
