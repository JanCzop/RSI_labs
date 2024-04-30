package com.example.lab05.Service_REST.Repository;



import com.example.lab05.Service_REST.Exceptions.CarBadStatusEx;
import com.example.lab05.Service_REST.Exceptions.CarExistsEx;
import com.example.lab05.Service_REST.Exceptions.CarNotFoundEx;
import com.example.lab05.Service_REST.Model.Car;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

public class CarRepositoryImpl implements CarRepository{

    private List<Car> cars;

    public CarRepositoryImpl(){
        cars = new ArrayList<>();
        int id = 0;
        cars.add(new Car(id,"126p","Fiat",1979, Car.Car_status.DAMAGED));
        cars.add(new Car(++id,"S-classe Coupe","Mercedes",1996, Car.Car_status.DAMAGED));
        cars.add(new Car(++id,"Carrera GT","Porsche",2005, Car.Car_status.DAMAGED));
        cars.add(new Car(++id, "Mustang", "Ford", 1965, Car.Car_status.UNDAMAGED));
        cars.add(new Car(++id, "Civic", "Honda", 2000, Car.Car_status.UNDAMAGED));
        cars.add(new Car(++id, "Golf", "Volkswagen", 1990, Car.Car_status.UNDAMAGED));
        cars.add(new Car(++id, "Corvette", "Chevrolet", 1960, Car.Car_status.UNDAMAGED));
        cars.add(new Car(++id, "911", "Porsche", 1975, Car.Car_status.UNDAMAGED));
        cars.add(new Car(++id, "Challenger", "Dodge", 1970, Car.Car_status.UNDAMAGED));
        cars.add(new Car(++id, "MX-5", "Mazda", 1990, Car.Car_status.UNDAMAGED));
    }
    @Override
    public List<Car> get_all_cars() {
        return cars;
    }

    @Override
    public Car get_car(int id) throws CarNotFoundEx {
        for (Car car : cars) {
            if(car.getId() == id) return car;
        }
        throw new CarNotFoundEx(id);
    }

    @Override
    public Car add_car(Car car_to_add) throws CarExistsEx {
        for (Car car : cars) {
            if(car.getId() == car_to_add.getId()) throw new CarExistsEx("Cannot add: \" + person.getId() + \" - there is a car with this id");
        }
        cars.add(car_to_add);
        return car_to_add;
    }

    @Override
    public boolean delete_car(int id) throws CarNotFoundEx {
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            if (car.getId() == id) {
                cars.remove(i);
                return true;
            }
        }
        throw new CarNotFoundEx(id);
    }


    @Override
    public Car update_car(Car car_to_upd) throws CarNotFoundEx {
        for (Car car : cars) {
            if (car.getId() == car_to_upd.getId()) {
                car.setModel(car_to_upd.getModel());
                car.setBrand(car_to_upd.getBrand());
                car.setProduction_year(car_to_upd.getProduction_year());
                car.setStatus(car_to_upd.getStatus());
                return car;
            }
        }
        throw new CarNotFoundEx(car_to_upd.getId());
    }

    @Override
    public int count_cars() {
        return cars.size();
    }

    @Override
    public Car damage_car(int id) throws CarNotFoundEx, CarBadStatusEx {
        for (Car car : cars) {
            if(car.getId() == id){
                if(car.getStatus() == Car.Car_status.UNDAMAGED){
                    car.setStatus(Car.Car_status.DAMAGED);
                    return car;
                }
                else throw new CarBadStatusEx(car.getStatus());
            }
        }
        throw new CarNotFoundEx(id);
    }

    @Override
    public Car repair_car(int id) throws CarNotFoundEx, CarBadStatusEx {
        for (Car car : cars) {
            if(car.getId() == id){
                if(car.getStatus() == Car.Car_status.DAMAGED){
                    car.setStatus(Car.Car_status.UNDAMAGED);
                    return car;
                }
                else throw new CarBadStatusEx(car.getStatus());
            }
        }
        throw new CarNotFoundEx(id);
    }
}
