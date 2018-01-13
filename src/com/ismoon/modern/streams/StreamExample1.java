package com.ismoon.modern.streams;

import java.math.BigInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author ismoon on 2017-08-28.
 */
public class StreamExample1 {
    // 무한 Collection

    public static void main(String[] args) {
        // 1 ~ 9 까지
        IntStream.range(1, 10)
                 .forEach(i -> System.out.print(i + " "));
        // int의 최대값까지만 증가(seed 첫번째 값, 이전값을 계속 더 한다.), foreach는 iterate의 값을 하나씩 받아서 처리한다.
        IntStream.iterate(1, i -> i + 1)
                 . forEach(i -> System.out.print(i + " "));
        // BigInteger의 최대값까지 증가한다.
        Stream.iterate(BigInteger.ONE, i -> i.add(BigInteger.ONE))
              .forEach(i -> System.out.print(i + " "));

    }
}
