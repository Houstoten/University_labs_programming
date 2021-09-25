package com.houston;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import com.houston.Car.Car;

public class Main {

    public static String[] models = { "Toyota Camry", "Lexus NC", "Honda Civic", "Volkswagen Polo" };

    private Car[] cars;

    public Car[] getCars() {
        return cars;
    }

    public void setCars(Car[] cars) {
        this.cars = cars;
    }

    public static void main(String[] args) {
        Main main = new Main();
        
        System.out.println("Created 10 cars: ");

        main.setCars(createCars(10));

        Arrays.stream(main.getCars()).forEach(System.out::println);

        System.out.println("\nFind all cars by model "+ models[0]+ ": ");

        Arrays.stream(main.getCarsByModel(models[0])).forEach(System.out::println);

        System.out.println("\nFind all cars over age 10: ");

        Arrays.stream(main.getCarsOverSomeAge(10)).forEach(System.out::println);

        System.out.println("\nFind all cars of year 2002 and over price 15000: ");

        Arrays.stream(main.getCarsOfYearBiggerThanPrice(2002, new BigDecimal(15000))).forEach(System.out::println);

    }

    public static Car[] createCars(int N) {
        Car[] res = new Car[N];

        Random random = new Random();

        BiFunction<Integer, Integer, Integer> randomIntFn = (max, min) -> random.nextInt(max - min) + min;

        for (int i = 0; i < N; i++) {
            res[i] = new Car(i, models[randomIntFn.apply(models.length - 1, 0)],
                    LocalDateTime.now().getYear() - randomIntFn.apply(20, 5),
                    new BigDecimal(randomIntFn.apply(30000, 10000)), i * 13);
        }

        return res;
    }

    public Car[] getCarsByModel(String model) {
        return filterCarsGeneric(car -> model.equals(car.getModel()));
    }

    public Car[] getCarsOverSomeAge(int years) {
        return filterCarsGeneric(car -> LocalDateTime.now().getYear() - car.getYear() >= years);
    }

    public Car[] getCarsOfYearBiggerThanPrice(int year, BigDecimal price) {

        Predicate<Car> predicate = car -> car.getYear() == year;

        predicate.and(car -> car.getPrice().compareTo(price) == -1);

        return filterCarsGeneric(predicate);
    }

    public Car[] filterCarsGeneric(Predicate<Car> predicate) {
        return Arrays.stream(cars).filter(predicate).toArray(Car[]::new);
    }

}