package com.ismoon.modern.functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * @author ismoon on 2017-08-28.
 */
public class IdentityFunctionExample {
    public static void main(String[] args) {
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(mapOld(numbers, integer -> integer * 2));
        System.out.println(mapOld(numbers, null));

        System.out.println(map(numbers, integer -> integer * 2));
        //아래는 같은 결과를 리턴한다.
        System.out.println(map(numbers, i -> i));
        System.out.println(map(numbers, Function.identity()));

    }

    // 1 매번 mapper에 null 체크를 한다.
    // 2 코드가 더럽다. ((R) t) 복잡해짐 코드이해가 어려워짐, 유지보수가 쉽지않음
    private static <T, R> List<R> mapOld(List<T> list, Function<T, R> mapper) {
        final List<R> result = new ArrayList<>();
        for (final T t : list)
            if (mapper != null) {
                result.add(mapper.apply(t));
            } else {
                result.add((R) t);
            }
        return result;
    }

    private static <T, R> List<R> map(final List<T> list, final Function<T, R> mapper) {
        final List<R> result = new ArrayList<>();
        for (final T t : list) {
            result.add(mapper.apply(t));
        }

        return result;
    }
}
