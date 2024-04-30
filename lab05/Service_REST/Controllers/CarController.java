package com.example.lab05.Service_REST.Controllers;

import com.example.lab05.Service_REST.Exceptions.CarBadStatusEx;
import com.example.lab05.Service_REST.Exceptions.CarExistsEx;
import com.example.lab05.Service_REST.Exceptions.CarNotFoundEx;
import com.example.lab05.Service_REST.Model.Car;
import com.example.lab05.Service_REST.Repository.CarRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {

    private final CarRepositoryImpl data = new CarRepositoryImpl();

    @GetMapping("/cars")
    public List<Car> get_all_cars() {
        return data.get_all_cars();
    }

    @GetMapping("/cars/{id}")
    public Car get_car(@PathVariable int id) {
        return data.get_car(id);
    }

    @PostMapping("/cars")
    public ResponseEntity<Car> add_car(@RequestBody Car car) {
        data.add_car(car);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @PutMapping("/cars/{id}")
    public Car update_car(@RequestBody Car car) {
        return data.update_car(car);
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Void> delete_car(@PathVariable int id) {
        data.delete_car(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/cars/{id}/damage")
    public Car damage_car(@PathVariable int id) {
        return data.damage_car(id);
    }

    @PatchMapping("/cars/{id}/repair")
    public Car repair_car(@PathVariable int id) {
        return data.repair_car(id);
    }
}
