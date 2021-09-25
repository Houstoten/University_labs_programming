package com.houston.Car;

import java.math.BigDecimal;

public class Car {
    private final int id;

    private final String model;

    private final int year;

    private BigDecimal price;

    private int registryNumber;

    public Car(int id, String model, int year) {
        this.id = id;
        this.model = model;
        this.year = year;
    }

    public Car(int id, String model, int year, BigDecimal price, int registryNumber) {
        this.id = id;
        this.model = model;
        this.year = year;
        this.price = price;
        this.registryNumber = registryNumber;
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getRegistryNumber() {
        return registryNumber;
    }

    public void setRegistryNumber(int registryNumber) {
        this.registryNumber = registryNumber;
    }

    @Override
    public String toString() {
        return String.format("\nCar: \nid: %s\nmodel: %s\nyear: %s\nprice: %s\nregistry number: %s", id, model, year,
                price, registryNumber);
    }
}
