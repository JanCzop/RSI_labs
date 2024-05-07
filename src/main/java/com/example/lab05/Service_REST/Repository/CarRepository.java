package com.example.lab05.Service_REST.Repository;



import com.example.lab05.Service_REST.Exceptions.CarBadStatusEx;
import com.example.lab05.Service_REST.Exceptions.CarExistsEx;
import com.example.lab05.Service_REST.Exceptions.CarNotFoundEx;
import com.example.lab05.Service_REST.Model.Car;

import java.util.List;

public interface CarRepository {
    List<Car> get_all_cars();
    Car get_car(int id) throws CarNotFoundEx;
    Car add_car(Car car) throws CarExistsEx;
    boolean delete_car(int id) throws CarNotFoundEx;
    Car update_car(Car car) throws CarNotFoundEx;
    int count_cars();
    Car damage_car(int id) throws CarNotFoundEx, CarBadStatusEx;
    Car repair_car(int id) throws CarNotFoundEx, CarBadStatusEx;
}
