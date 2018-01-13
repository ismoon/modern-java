package com.ismoon.modern.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ismoon on 2017-09-01.
 */
public class StreamExample3 {
    public static void main(String[] args) {
        // Lazy (결과를 요청할 때 데이터를 넘긴다.)
        // Inter Mediate Operation Method(중간단계 : filter, map 등)
        //      : Stream을 리턴하기 때문에 계속 Method Chaining을 통해서 무엇을 해야할지 Stream에게 지시할 수 있다.

        // Terminal Operation Method(findFirst 등) : 중간단계를 처리한 Collection Optional 객체 등을 리턴한다.
        System.out.println("collect(toList()) : " +
                Stream.of(1, 3, 3, 5, 5) //.count();//list의 size 와 같다.
                        .filter(i -> i > 2)
                        .map(i -> i * 2)
                        .map(i -> "#" + i)
                        .collect(Collectors.toList())
        );

        System.out.println("collect(toSet()) : " +
                Stream.of(1, 3, 3, 5, 5)
                        .filter(i -> i > 2)
                        .map(i -> i * 2)
                        .map(i -> "#" + i)
                        .collect(Collectors.toSet())
        );

        System.out.println("collect(joining()) : " +
                Stream.of(1, 3, 3, 5, 5)
                        .filter(i -> i > 2)
                        .map(i -> i * 2)
                        .map(i -> "#" + i)
                        .collect(Collectors.joining()) // 연결해서 스트링을 만들어 준다.
        );

        System.out.println("collect(joining(\", \")) : " +
                Stream.of(1, 3, 3, 5, 5)
                        .filter(i -> i > 2)
                        .map(i -> i * 2)
                        .map(i -> "#" + i)
                        .collect(Collectors.joining(", ")) // 연결해서 스트링을 만들어 준다.
        );

        System.out.println("collect(joining(\", \", \"[\", \"]\")) : " +
                Stream.of(1, 3, 3, 5, 5)
                        .filter(i -> i > 2)
                        .map(i -> i * 2)
                        .map(i -> "#" + i)
                        .collect(Collectors.joining(", ", "[", "]")) // 연결해서 스트링을 만들어 준다.
        );

        System.out.println("distinct().collect(joining(\", \", \"[\", \"]\")) : " +
                Stream.of(1, 3, 3, 5, 5)
                        .filter(i -> i > 2)
                        .map(i -> i * 2)
                        .map(i -> "#" + i)
                        .distinct()             // 중복을 제거한 유일한 값들만 남는다.
                        .collect(Collectors.joining(", ", "[", "]")) // 연결해서 스트링을 만들어 준다.
        );

        System.out.println("distinct().collect(toList()) : " +
                Stream.of(1, 3, 3, 5, 5) //.count();//list의 size 와 같다.
                        .filter(i -> i > 2)
                        .map(i -> i * 2)
                        .map(i -> "#" + i)
                        .distinct()
                        .collect(Collectors.toList())
        );

        //Integer.valueOf()메소드에서 -128 ~ 127 까지는 캐싱이 되어있어 같은 메모리 레퍼런스를 가지고 있기 때문에 가능
        final Integer integer3 = 3;
        System.out.println(
                Stream.of(1, 2, 3, 4, 5)
                        .filter(i -> i == integer3)
                        .findFirst()


        );

        final Integer integer127 = 127;
        System.out.println(
                Stream.of(1, 2, 3, 4, 5, 127)
                        .filter(i -> i == integer127)
                        .findFirst()


        );

        //Integer.valueOf()메소드에서 -128 ~ 127 까지는 캐싱이 되어있기 Optional.empty 가 나옴
        final Integer integer128 = 128;
        System.out.println(
                Stream.of(1, 2, 3, 4, 5, 128)
                        .filter(i -> i == integer128)
                        .findFirst()


        );

        //때문에 Object.equals()를 사용하는게 안전하다.
        System.out.println(
                Stream.of(1, 2, 3, 4, 5, 128)
                        .filter(i -> i.equals(integer128))
                        .findFirst()
        );


        System.out.println(".filter(i-> i > integer3).count() : " +
                Stream.of(1, 2, 3, 4, 5)
                        .filter(i -> i > integer3) // >= <= > < 는 가능
                        .count()
        );

        System.out.println("for (Integer i : numbers)");
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        // 외부에서 루프를 이용하는 경우 External Iterator
        for (Integer i : numbers){
            System.out.print(i + " ");
        }

        System.out.println("forEach(i -> System.out.print(i + \" \"))");
        // 내부에서 루프를 이용하는 경우 Internal Iterator
        //** Stream은 Internal Iterator 이다.
        numbers.stream().forEach(i -> System.out.print(i + " "));
    }
}
