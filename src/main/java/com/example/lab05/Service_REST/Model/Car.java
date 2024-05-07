package com.example.lab05.Service_REST.Model;

public class Car {
    private int id;
    private String model;
    private String brand;
    private int production_year;
    private Car_status status;

    public enum Car_status{
        DAMAGED,
        UNDAMAGED
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", production_year=" + production_year +
                ", status=" + status +
                '}';
    }

    public Car(int id, String model, String brand, int production_year, Car_status status) {
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.production_year = production_year;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getProduction_year() {
        return production_year;
    }

    public void setProduction_year(int production_year) {
        this.production_year = production_year;
    }

    public Car_status getStatus() {
        return status;
    }

    public void setStatus(Car_status status) {
        this.status = status;
    }
}
