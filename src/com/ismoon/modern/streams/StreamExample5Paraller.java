package com.ismoon.modern.streams;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author ismoon on 2017-09-06.
 */
public class StreamExample5Paraller {
    public static void main(String[] args) {
        final int[] sum = {0};
        IntStream.range(0, 100)
                .forEach(i -> sum[0] += i);
        System.out.println("               sum (with side-effect): " + sum[0]);

        final int[] sum2 = {0};
        IntStream.range(0, 100)
                .parallel()
                .forEach(i -> sum2[0] += i);
        System.out.println("      parallel sum (with side-effect): " + sum2[0]);

        System.out.println("         stream sum (no side-effect) : " +
                IntStream.range(0, 100)
                        .sum()
        );

        System.out.println("parallel stream sum (no side-effect) : " +
                IntStream.range(0, 100)
                        .parallel()
                        .sum()
        );


        System.out.println("\n=======================================");
        System.out.println("Stream");

//        final long start = System.currentTimeMillis();
//        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
//                .stream()
//                .map(integer -> {
//                    try {
//                        TimeUnit.SECONDS.sleep(1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    return integer;
//                })
//                .forEach(System.out::println);
//        System.out.println(System.currentTimeMillis() - start);

        System.out.println("\n=======================================");
        System.out.println("Parallel Stream (8 elements)");
        final long start2 = System.currentTimeMillis();
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
                .parallelStream()
                .map(integer -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return integer;
                })
                .forEach(System.out::println);
        System.out.println(System.currentTimeMillis() - start2);

        System.out.println("\n=======================================");
        System.out.println("Parallel Stream (9 elements)");
        final long start3 = System.currentTimeMillis();
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .parallelStream()
                .map(integer -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return integer;
                })
                .forEach(System.out::println);
        System.out.println(System.currentTimeMillis() - start3);

        System.out.println("\n=======================================");
        System.out.println("Parallel Stream (8 elements) with parallelism : 7");
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "7"); // 0 : 1, 1 : 2 ....
        final long start4 = System.currentTimeMillis();
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
                .parallelStream()
                .map(integer -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return integer;
                })
                .forEach(System.out::println);
        System.out.println(System.currentTimeMillis() - start4);

        final Stream<Integer> concat = Stream.concat(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8).stream(), Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8).stream());
        System.out.println(concat.collect(Collectors.toList()));

        final Stream<Integer> stream1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8).stream();
        final Stream<Integer> stream2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8).stream();


//        System.out.println("\n=======================================");
//        System.out.println("Parallel Stream (8 elements) with parallelism : 3");
//        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "3"); // 0 : 1, 1 : 2 ....
//        final long start5 = System.currentTimeMillis();
//        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
//                .parallelStream()
//                .map(integer -> {
//                    try {
//                        TimeUnit.SECONDS.sleep(1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    return integer;
//                })
//                .forEach(System.out::println);
//        System.out.println(System.currentTimeMillis() - start5);
    }
}
