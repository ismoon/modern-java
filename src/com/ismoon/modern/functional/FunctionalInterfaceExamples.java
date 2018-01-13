package com.ismoon.modern.functional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author ismoon on 2017-08-31.
 */
public class FunctionalInterfaceExamples {
    private void run4FunctionalInterfaces() {
        final Function<String, Integer> toInt = Integer::parseInt;
        System.out.println(toInt.apply("100"));

        final Consumer<String> print = System.out::println;
        print.accept("Hello!");

        final Predicate<Integer> isPositive = integer -> integer > 0;
        System.out.println(isPositive.test(1));
        System.out.println(isPositive.test(0));
        System.out.println(isPositive.test(-11));

        List<Integer> numbers = Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5);

        System.out.println(filter(numbers, isPositive));
        System.out.println(filter(numbers, i -> i < 3));

        final Supplier<String> helloSupplier = () -> "Hello";
        System.out.println(helloSupplier.get());

//        printIfValidIndex(0, "Insu");
//        printIfValidIndex(-1, "Insu");

        long start = System.currentTimeMillis();
        printIfValidIndex(0, FunctionalInterfaceExamples::getVeryExpensiveValue);
        printIfValidIndex(-1, FunctionalInterfaceExamples::getVeryExpensiveValue);
        printIfValidIndex(-2, FunctionalInterfaceExamples::getVeryExpensiveValue);
        System.out.println("It took " + ((System.currentTimeMillis() - start) / 1000) + " seconds");
    }

    private static String getVeryExpensiveValue() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Insu";
    }

    //    private static void printIfValidIndex(int number, String value){
    private static void printIfValidIndex(int number, Supplier<String> valueSupplier) {
        if (number >= 0)
            System.out.println("The value is " + valueSupplier.get() + ".");
        else
            System.out.println("Invalid");
    }

    private static <T> List<T> filter(List<T> list, Predicate<T> filter) {
        List<T> result = new ArrayList<>();
        for (T input : list) {
            if (filter.test(input))
                result.add(input);
        }
        return result;
    }

    public static void main(String[] args) {
        println(1, 2, 3, (i1, i2, i3) -> String.valueOf(i1 + i2 + i3));
        println("Area is ", 12, 20, (message, length, width) -> message + (length * width));
        println(1L, "insu", "insu4341@gmail.com", (id, name, email) -> "User info : ID = " + id + ", Name = " + name + ", Email = " + email);

        BigDecimalToCurrency bigDecimalToCurrency = bd -> "$" + bd.toString();
        System.out.println(bigDecimalToCurrency.toCurrency(new BigDecimal("120.00")));

        InvalidFunctionalInterface anonymousClass = new InvalidFunctionalInterface(){
            @Override
            public <T> String mkString(T value) {
                return value.toString();
            }
        };

        System.out.println("anonymous class : " + anonymousClass.mkString(123));

        // 제네릭 메소드는 Functional Interface로 사용할 수 없다.
//        InvalidFunctionalInterface invalidFunctionalInterface = value -> value.toString();
//        System.out.println(invalidFunctionalInterface.mkString(123));
    }

    private static <T1, T2, T3> void println(T1 t1, T2 t2, T3 t3, Function3<T1, T2, T3, String> function){
        System.out.println(function.apply(t1, t2, t3));
    }
}

@FunctionalInterface
interface Function3<T1, T2, T3, R> {
    R apply(T1 t1, T2 t2, T3 t3);
}

//입력값이 명확한 경우
@FunctionalInterface
interface BigDecimalToCurrency{
    String toCurrency(BigDecimal value);
}

@FunctionalInterface
interface InvalidFunctionalInterface{
    <T> String mkString(T value);
}