package com.ismoon.modern.method_reference;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * @author ismoon
 * @since 2017-11-10.
 */
public class MethodReferenceExamples2 {
    public static void main(String[] args) {
        System.out.println("=====================================================");
        methodReference03();
        System.out.println("=====================================================");
    }

    private static void methodReference03() {
        // First Class Function

        // 함수의 인자로 Function을 전달
        // Using Lambda Expression
        System.out.println(testFirstClassFunction1(3, i -> String.valueOf(i * 2)));
        // Using Method Reference
        System.out.println(testFirstClassFunction1(3, MethodReferenceExamples2::doubleThenToString));
        System.out.println("=====================================================");

        // 함수에서 Function을 리턴
        // Using Lambda Expression
        final Function<Integer, String> fl = getDoubleThenToStringUsingLambdaExpression();
        final String resultFromFl = fl.apply(3);
        System.out.println(resultFromFl);
        // Using Method Reference
        final Function<Integer, String> fmr = getDoubleThenToStringUsingMethodReference();
        final String resultFromFmr = fmr.apply(3);
        System.out.println(resultFromFmr);

        System.out.println("=====================================================");
        // Function을 Collection에 저장
        // Using Lambda Expression
        final List<Function<Integer, String>> fsL = Arrays.asList( i -> String.valueOf(i * 2));
        for (final Function<Integer, String> f : fsL) {
            final String result = f.apply(3);
            System.out.println(result);
        }
        // Using Method Reference
        final List<Function<Integer, String>> fsMr = Arrays.asList(MethodReferenceExamples2::doubleThenToString);
        for (final Function<Integer, String> f : fsMr) {
            final String result = f.apply(3);
            System.out.println(result);
        }

        System.out.println("=====================================================");
        // Function을 변수에 저장
        // Using Lambda Expression
        final Function<Integer, String> fl2 = i -> String.valueOf(i * 2);
        final String resultFl2 = fl2.apply(5);
        System.out.println(resultFl2);
        // Using Method Reference
        final Function<Integer, String> fmr2 = MethodReferenceExamples2::doubleThenToString;
        final String resultFmr2 = fmr2.apply(5);
        System.out.println(resultFmr2);

    }

    private static String doubleThenToString(int i) {
        return String.valueOf(i * 2);
    }

    private static String testFirstClassFunction1(int n, Function<Integer, String> f) {
        return "The result is " + f.apply(n) + ".";
    }

    private static Function<Integer, String> getDoubleThenToStringUsingLambdaExpression() {
        return  i -> String.valueOf(i * 2);
    }

    private static Function<Integer, String> getDoubleThenToStringUsingMethodReference() {
        return MethodReferenceExamples2::doubleThenToString;
    }


}
