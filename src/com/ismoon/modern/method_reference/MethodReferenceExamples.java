package com.ismoon.modern.method_reference;

import java.math.BigDecimal;
import java.util.Arrays;

import static java.util.stream.Collectors.toList;

/**
 * @author ismoon
 * @since 2017-11-03.
 */
public class MethodReferenceExamples {
    public static void main(String[] args) {
        Arrays.asList(1, 2, 3, 4, 5)
//                .forEach(i -> System.out.println(i))
                .forEach(System.out::println);

        System.out.println( "Static Method : " +
                Arrays.asList(new BigDecimal("10.0"), new BigDecimal("23"), new BigDecimal("5"))
                        .stream()
                        .sorted(BigDecimalUtil::compare)
                        .collect(toList())
        );

        System.out.println("Object Method : " +
                Arrays.asList(new BigDecimal("10.0"), new BigDecimal("23"), new BigDecimal("5"))
                        .stream()
//                        .sorted((bd1, bd2) -> bd1.compareTo(bd2))
//                        .sorted(BigDecimalUtil::compare)
                        .sorted(BigDecimal::compareTo)
                        .collect(toList())
        );

        final String targetString = "c";
        System.out.println("targetString Object Method : " +
                Arrays.asList("a", "b", "c", "d")
                        .stream()
//                        .anyMatch(x -> x.equals("c"))
//                        .anyMatch(x -> targetString.equals(x))
                        .anyMatch(targetString::equals)
        );
    }
}

class BigDecimalUtil {
    public static int compare(BigDecimal bd1, BigDecimal bd2) {
         return bd1.compareTo(bd2);
    }
}
