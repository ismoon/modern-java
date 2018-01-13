package com.ismoon.modern.closure;

/**
 * @author ismoon
 * @since 2017-10-27.
 */
public class ClosureExample {
    private int number = 999;

    public static void main(String[] args) {

        final ClosureExample closureExample = new ClosureExample();
//        closureExample.test1();
//        closureExample.test2();
//        closureExample.test3();
        closureExample.test4();
    }

    private void test() {
        int number = 100;

        testClosure("Anonymous Class", new Runnable() {
            @Override
            public void run() {
                System.out.println(number);
            }
        });

        testClosure("Lambda Expression", () -> System.out.println(number));
    }


    private void test1() {
        int number = 100; // 없으면 필드를 가르킨다.

        // number가 final이 아니면 접근을 못한다 하지만 java8에서는 final이 아니여도 접근이 가능하다.
        // 하지만 final이 아니라고 값을 변경하려고 하면 에러가 발생한다. (사실 final인데 final이 아닌것 처럼 보인다. / Effectively Final)
//        number = 1; // 에러발생
        testClosure("Anonymous Class", new Runnable() {
            @Override
            public void run() {
//                number = 1; //에러발생
                //this.number : new Runnable()의 this를 가르킨다.
                System.out.println(ClosureExample.this.number);
//                this.toString();// Runnable  오브젝트의 toString
            }
        });

        //this.number :this는 람다식의 람다오브젝트가 아닌 람다오브젝트를 가지고 있는 오브젝의 this이다
        // 람다 오브젝에 대한 scope이 없이 없기 때문에 람다 오브젝에 대한 this는 절대 접근 할 수 없다.
        testClosure("Lambda Expression", () -> System.out.println(this.number)); //this.toString();//람다오브젝트를 가지고 있는 오브젝트의 toString
    }

    private void test2() {
        testClosure("Anonymous Class1", new Runnable() {
            @Override
            public void run() {
                System.out.println(this.toString());
            }
        });

        testClosure("Anonymous Class2", new Runnable() {
            @Override
            public void run() {
                System.out.println(ClosureExample.this.toString());
            }
        });

        testClosure("Lambda Expression", () -> System.out.println(this.toString()));
    }

    private void test3() {
        testClosure("Anonymous Class2", new Runnable() {
            @Override
            public void run() {
//                내부의 toString에 static <T> String toString(T value) 가 없기 때문에 static이여도
//                ClosureExample의 static <T> String toString(T value)를 찾을 수 없다.
//                System.out.println(toString("Test"));
                System.out.println(ClosureExample.toString("Test"));
            }
        });

        testClosure("Lambda Expression", () -> System.out.println(toString("Test")));
    }

    private void test4() {
        int number = 100; // 없으면 필드를 가르킨다.

        testClosure("Anonymous Class", new Runnable() {
            @Override
            public void run() {
//                int number = 50; no compile-time error
                System.out.println(number);
            }
        });

        testClosure("Lambda Expression", () -> {
//            int number = 50; // 해당 변수가 이미 스코프안에 정의 되어있어서 에러가 발생.
            System.out.println(number);
        });
    }

    private static void testClosure(final String name, Runnable runnable) {
        System.out.println("====================================");
        System.out.println(name + " : ");
        runnable.run();
        System.out.println("====================================");
    }

    @Override
    public String toString() {
        return "ClosureExample";
    }

    public static <T> String toString(T value) {
        return "The Value is" + String.valueOf(value);
    }
}
