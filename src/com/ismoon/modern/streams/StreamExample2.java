package com.ismoon.modern.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author ismoon on 2017-08-29.
 */
public class StreamExample2 {
    public static void main(String[] args) {
        //Stream은 Collection Builder 이다.
        //게으른 빌더이다?? 달라고 하기 전까지 결과를 주지 않는다?? 불필요한 계산을 줄이고 효율적이다.
        Stream.of(1); // 하나의 값을 가지는 스트림
        Stream.of(1, 2, 3, 4, 5)
                .forEach(System.out::println); // 여러개의 값을 가지는 스트림
        Stream.empty();// 비어있는 스트림

        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("Functional Result: " +
                numbers.stream()
                    .filter(number -> number > 3)
                    .filter(number -> number < 9)
                    .map(number -> number * 2)
                    .filter(number -> number > 10)
                    .findFirst()
        );
    }
}
