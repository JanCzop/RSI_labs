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
                            links.add(linkTo(methodOn(CarController.class).repair_car(car)).withRel("firecar"));
                        } else if (car.getStatus() == Car.Car_status.UNDAMAGED) {
                            links.add(linkTo(methodOn(CarController.class).damage_car(car)).withRel("hirecar"));
                            links.add(linkTo(methodOn(CarController.class).delete_car(car.getId())).withRel("deletecar"));
                        }
                        else {
                            links.add(linkTo(methodOn(CarController.class).delete_car(car.getId())).withRel("deletecar"));
                        }
                        links.add(linkTo(methodOn(CarController.class).get_all_cars()).withRel("list all"));

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
    public EntityModel<?> get_car(@PathVariable int id) {
        try {
            System.out.println("...called GET"); //dla śledzenia działania
            Car car = data.get_car(id);
            if (car.getStatus().equals(Car.Car_status.DAMAGED)) {
                return EntityModel.of(car,
                        linkTo(methodOn(CarController.class)
                                .get_car(car.getId())).withSelfRel(),
                        linkTo(methodOn(CarController.class)
                                .repair_car(car)).withRel("firecar"),
                        linkTo(methodOn(CarController.class)
                                .get_all_cars()).withRel("list all") );
            }
            if (car.getStatus().equals(Car.Car_status.UNDAMAGED)) {
                return EntityModel.of(car,
                        linkTo(methodOn(CarController.class)
                                .get_car(car.getId())).withSelfRel(),
                        linkTo(methodOn(CarController.class)
                                .get_car(car.getId())).withRel("hirecar"),
                        linkTo(methodOn(CarController.class)
                                .get_car(car.getId())).withRel("deletecar"),
                        linkTo(methodOn(CarController.class)
                                .get_all_cars()).withRel("list all"));
            }
            else {
                return EntityModel.of(car,
                        linkTo(methodOn(CarController.class)
                                .get_car(car.getId())).withSelfRel(),
                        linkTo(methodOn(CarController.class)
                                .delete_car(car.getId())).withRel("deletecar"),
                        linkTo(methodOn(CarController.class)
                                .get_all_cars()).withRel("list all"));
            }
        } catch (CarNotFoundEx e) {
            System.out.println("...GET Exception");
            throw e;
        }
    }

    @PostMapping("/cars")
    public EntityModel<Car> add_car(@RequestBody Car car) throws CarExistsEx {
        try {
            System.out.println("...called ADD"); //dla śledzenia działania
            data.add_car(car);
            return EntityModel.of(car,
                    linkTo(methodOn(CarController.class)
                            .get_car(car.getId())).withSelfRel(),
                    linkTo(methodOn(CarController.class)
                            .delete_car(car.getId())).withRel("deletecar"),
                    linkTo(methodOn(CarController.class)
                            .get_all_cars()).withRel("list all"));

        } catch (CarExistsEx e) {
            System.out.println("...ADD Exception");
            throw e;
        }
    }

    @PutMapping("/cars/{id}")
    public EntityModel<?> update_car(@RequestBody Car car) {
        try {
            System.out.println("...called PUT"); //dla śledzenia działania
            Car updatedcar = data.update_car(car);
            return EntityModel.of(updatedcar,
                    linkTo(methodOn(CarController.class)
                            .get_car(car.getId())).withSelfRel(),
                    linkTo(methodOn(CarController.class)
                            .delete_car(car.getId())).withRel("deletecar"),
                    linkTo(methodOn(CarController.class)
                            .get_all_cars()).withRel("list all") );

        } catch (CarNotFoundEx e) {
            System.out.println("...PUT Exception");
            throw e;
        }
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Boolean> delete_car(@PathVariable int id) {
        try {
            System.out.println("...called DELETE"); //dla śledzenia działania
            data.delete_car(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (CarNotFoundEx e) {
            System.out.println("...DELETE Exception");
            throw e;
        }
    }

    @PatchMapping("/cars/{id}/damage")
    public EntityModel<?> damage_car(@RequestBody Car car) {
        try {
            System.out.println("...called damage");
            data.damage_car(car);
            return get_car(car.getId());
        } catch (CarBadStatusEx e) {
            System.out.println("...damage Exception");
            throw e;
        }
    }

    @PatchMapping("/cars/{id}/repair")
    public EntityModel<?> repair_car(@RequestBody Car car) {
        try {
            System.out.println("...called repair");
            data.repair_car(car);
            return get_car(car.getId());
        } catch (CarBadStatusEx e) {
            System.out.println("...repair Exception");
            throw e;
        }
    }

}
