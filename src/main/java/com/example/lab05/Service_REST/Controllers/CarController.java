package com.example.lab05.Service_REST.Controllers;

import com.example.lab05.Service_REST.Exceptions.CarBadStatusEx;
import com.example.lab05.Service_REST.Exceptions.CarExistsEx;
import com.example.lab05.Service_REST.Exceptions.CarNotFoundEx;
import com.example.lab05.Service_REST.Model.Car;
import com.example.lab05.Service_REST.Repository.CarRepositoryImpl;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CarController {

    private final CarRepositoryImpl data = new CarRepositoryImpl();

    @GetMapping("/cars")
    public CollectionModel<EntityModel<Car>> get_all_cars() {
        try {
            System.out.println("...called GET ALL"); //dla śledzenia działania
            List<EntityModel<Car>> cars = data.get_all_cars()
                    .stream()
                    .map(car -> {
                        List<Link> links = new ArrayList<>();
                        links.add(linkTo(methodOn(CarController.class).get_car(car.getId())).withSelfRel());

                        if (car.getStatus() == Car.Car_status.DAMAGED) {
                            links.add(linkTo(methodOn(CarController.class).repair_car(car.getId())).withRel("repair"));
                        } else if (car.getStatus() == Car.Car_status.UNDAMAGED) {
                            links.add(linkTo(methodOn(CarController.class).damage_car(car.getId())).withRel("damage"));
                        }
                        return EntityModel.of(car, links);
                    })
                    .collect(Collectors.toList());

            return CollectionModel.of(cars, linkTo(methodOn(CarController.class).get_all_cars()).withSelfRel());
        } catch (CarNotFoundEx e) {
            System.out.println("...GET ALL Exception");
            throw e;
        }
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
