package com.houston;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Lucas numbers solver class
 */
public class LucasSolver {

    /**
     * predicate to filter lucas numbers
     */
    private final Predicate<Long> predicate;

    /**
     * Constructs LucasSolver instance with specified predicate
     * 
     * @param predicate the initial predicate to use in filtering
     */
    public LucasSolver(Predicate<Long> predicate) {
        this.predicate = predicate;
    }

    /**
     * Default predicate which checks if number is cube. Variant4 individual task.
     */
    public static NamedPredicate<Long> checkIfCube = new NamedPredicate<>(num -> {
        double decubed = Math.cbrt(num);
        return decubed == Math.rint(decubed);
    }, "Test for cube");

    /**
     * Entry point method
     */
    public static void main(String[] args) {
        int N = 0;

        try {
            N = Integer.parseInt(args[0]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            System.out.print("Enter number of Lucas numbers: ");
            Scanner in = new Scanner(System.in);

            while (true) {
                try {

                    N = in.nextInt();
                    in.close();

                    break;
                } catch (InputMismatchException e) {
                    System.out.print("Input is NOT integer! Enter right one: ");

                    in.next();
                }
            }
        }

        Predicate<Long> predicate = LucasSolver.checkIfCube;

        LucasSolver solver = new LucasSolver(predicate);

        long[] res = solver.solve(N);

        System.out.println("\nInput number of lucas numbers to process: " + N);
        System.out.println("Found lucas numbers fit predicate that uses '" + predicate + "' check: [ "
                + Arrays.stream(res).mapToObj(Long::toString).collect(Collectors.joining(", ")) + " ]");
    }

    /**
     * Function that solves lucas numbers
     * 
     * @param N initial number of lucas numbers to precess
     * @return array of lucas numbers that fit predicate
     */
    public long[] solve(int N) {
        long a = 2, b = 1, current;

        List<Long> result = new ArrayList<>();

        Consumer<Long> appendConsumer = number -> {
            if (!result.contains(number) && predicate.test(number))
                result.add(number);
        };

        appendConsumer.accept(a);
        appendConsumer.accept(b);

        for (int i = 2; i < N; i++) {
            current = a + b;
            appendConsumer.accept(current);

            a = b;
            b = current;
        }

        return result.stream().mapToLong(Long::longValue).toArray();
    }

}

/**
 * Class which implement Predicate with String representation
 */
class NamedPredicate<T> implements Predicate<T> {

    /**
     * predicate to be used in test
     */
    private final Predicate<T> predicate;

    /**
     * Name to be returned in toString function
     */
    private final String name;

    /**
     * Constructs NamedPredicate instance 
     * 
     * @param predicate the initial predicate to use in filtering
     * @param name the initial name that represents predicate purpose
     */
    public NamedPredicate(Predicate<T> predicate, String name) {
        this.predicate = predicate;
        this.name = name;
    }

    /**
     * @param t predicate input to be tested
     */
    @Override
    public boolean test(T t) {
        return predicate.test(t);
    }

    /**
     * @return predicate string representation 
     */
    @Override
    public String toString() {
        return name;
    }
}