package com.sailfish.lambda;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.IntConsumer;

/**
 * @author sailfish
 * @create 2017-05-18-上午9:27
 */
public class DeclarativeDemo {

    @FunctionalInterface
    public static interface IntHandler{
        void handler(int i);
    }

    @FunctionalInterface
    public static interface DoubleHandler{
        void handler(double i);
//        void handler2(double i);
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8};
        Arrays.stream(array).forEach(System.out::println); //lambda表达式
        System.out.println("----------------");
        Arrays.stream(array).map(x -> (x % 2 == 0 ? x : x + 1)).forEach(System.out::println);
        System.out.println("------------------");

        Arrays.stream(array).forEach(new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.println(value);
            }
        });

        System.out.println("----------------");
        Arrays.stream(array).forEach((final int x) -> {
            System.out.println(x);
        });
        System.out.println("----------------");
        Arrays.stream(array).forEach((x) -> {
            System.out.println(x);
        });
        System.out.println("----------------");
        Arrays.stream(array).forEach((x) -> System.out.println(x));
        System.out.println("----------------");
        Arrays.stream(array).forEach(System.out::println);
        System.out.println("----------------");
        IntConsumer outprintln = System.out::println;
        IntConsumer errprintln = System.err::println;
        Arrays.stream(array).forEach(outprintln.andThen(errprintln));
    }


}
