package com.ismoon.modern.higher_order_function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * @author ismoon
 * @since 2017-11-02.
 */
public class HigherOrderFunctionExample {
    public static void main(String[] args) {
        final Function<Function<Integer, String>, String> f = g -> g.apply(10);
        System.out.println(
                f.apply(i -> "#" + i)
        );

        final Function<Integer, Function<Integer, Integer>> f2 = i -> (i2 -> i + i2);
        System.out.println(
                f2.apply(1).apply(9)
        );

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        final List<String> mappedList = map(list, i -> "#" + i);
        System.out.println(
                mappedList
        );

    }
    public static <T, R> List<R> map(List<T> list, Function<T, R> mapper) {
        final List<R> result = new ArrayList<>();

        for (final T t : list) result.add(mapper.apply(t));

        return result;
    }
}
