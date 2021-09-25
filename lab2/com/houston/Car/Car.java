package com.houston.Car;

import java.math.BigDecimal;

/**
 * Car class
 */
public class Car {

    /**
     * Car id
     */
    private final int id;

    /**
     * Car model
     */
    private final String model;

    /**
     * Car creation year
     */
    private final int year;

    /**
     * Car price
     */
    private BigDecimal price;

    /**
     * Car number in registry
     */
    private int registryNumber;

    /**
     * Constructs Car instance without specifying non-final fields
     * @param id initial id value
     * @param model initial model value
     * @param year initial year value
     */
    public Car(int id, String model, int year) {
        this.id = id;
        this.model = model;
        this.year = year;
    }

    /**
     * Constructs Car instance including non-final fields
     * @param id initial id value
     * @param model initial model value
     * @param year initial year value
     * @param price initial price value
     * @param registryNumber initial registry number value
     */
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
