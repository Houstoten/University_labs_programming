package com.houston;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
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

        LucasSolver solver = new LucasSolver(LucasSolver.checkIfCube);

        long[] res = solver.solve(N);

        System.out.println("\nFound cubes: [ "+ Arrays.stream(res).mapToObj(Long::toString).collect(Collectors.joining(", ")) + " ]");
    }
}

class LucasSolver {
    private Predicate<Long> predicate;

    public LucasSolver(Predicate<Long> predicate) {
        this.predicate = predicate;
    }

    public static Predicate<Long> checkIfCube = num -> {
        double decubed = Math.cbrt(num);
        return decubed == Math.rint(decubed);
    };

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
