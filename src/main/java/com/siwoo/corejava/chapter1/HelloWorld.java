package com.siwoo.corejava.chapter1;

import org.junit.Test;

import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }

    @Test
    public void systemOut() throws NoSuchMethodException {
        //System.out은 PrintStream 클래스의 인스턴스이며,
        //PrintStream에는 println, print 메서드가 있다.
        assertTrue(System.out instanceof PrintStream);
        assertNotNull(PrintStream.class.getMethod("print", String.class));
        assertNotNull(PrintStream.class.getMethod("println", String.class));

        //Random 클래스는 난수를 생성한다
        //2^32 제곱
        Random random = new Random();
        System.out.println(random.nextInt());
        System.out.println(random.nextBoolean());
    }

    @Test
    public void primitive() {
        //Primitive 랩퍼 클래스에는 최소, 최대값이 정의되어 있다.
        assertTrue(Byte.MIN_VALUE == -128);
        assertTrue(Byte.MAX_VALUE == 127);

        //16진수는 0x 접두어를 붙힌다.
        assertEquals(0xF, 15);
        assertEquals(0xA, 10);

        //2진수는 0b를 접두어를 붙힌다.
        assertEquals(0b10001, 17);
        assertEquals(0b1001, 9);

        //숫자 리터럴에 밑줄 _을 붙힐 수 있다.
        assertEquals(1_000_000 , 1000000);
        //Double.POSITIVE_INFINITY 은 양의 무한대를 의미한다.
        assertTrue(Double.isInfinite(1./0.));
        //Double.NaN 은 숫자가 아님을 의미한다.
        assertTrue(Double.isNaN(0./0.));

        //char 타입은 UTF-16 문자 인코딩을 사용한다.
        assertTrue('J' == 74);
        //유니코드는 \\u을 이용하며 표현한다.
        assertEquals('\u004a', 'J');
        System.out.println('\u263a');
    }

    enum WeekDay {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
    }

    @Test
    public void variable() {
        //변수에는 해당 타입 값만 저장할 수 있다.
        int total = 0, count;
        //변수는 반드시 초기화한 후 사용해야 한다.
//        if(total == 0) {
//            count = 0;
//        } else {
//            count++;
//        }
        //final 키워드는 상수를 의미하며, 대문자로 정의함이 관례이다
        assertEquals(Calendar.DAY_OF_WEEK, 7);
        //final의 값이 한번만 바뀐다는 룰만 존중한다면, 초기화를 미룰 수 있다.
        final int DAYS_FEBURARY;
        if(LocalDate.now().isLeapYear()) {
            DAYS_FEBURARY = 29;
        } else {
            DAYS_FEBURARY = 28;
        }
        //enum 이용하여 상수 집합을 정의할 수 있다.
        assertEquals(WeekDay.values().length, 7);
    }

    @Test
    public void operator() {
        // % 연산자는 나머지를 돌려준다.
        assertEquals(17 % 15, 2);
        // a % b가 0이라면 a는 b의 정수배이다.
        assertEquals(17 % 17, 0);
        assertEquals(34 % 17, 0);
        assertEquals(51 % 17, 0);
        assertEquals(52 % 17, 1);

        // n % 2 가 0이면 n은 짝수다.
        assertEquals(22 % 2, 0);
        // n % 2 가 1이면 홀수이면서 양수이다
        assertEquals(23 % 2, 1);
        // n % 2 가 -1이면 홀수이면서 음수이다.
        assertEquals(-23 % 2, -1);

        //시침의 간격을 0-11으로 표현할 시, 간격이 조정하는 메서드를 정의하라
        int newPostion = addAdjustment(11, 5);
        assertTrue(newPostion == 4);
        newPostion = addAdjustment(9, 5);
        assertTrue(newPostion == 2);

    }

    private int addAdjustment(int currentPosition, int adjustment) {
        return (currentPosition + adjustment) % 12;
    }

    @Test
    public void math() {
        //Math.pow은 제곱수를 만든다.
        assertEquals(Math.pow(2,4), 16, 0);
        //Math.sqrt은 제곱근을 만든다.
        assertEquals(Math.sqrt(49), 7, 0);

        //Math.min과 Math.max으로 최고값, 최소값을 구할 수 있다.
        assertEquals(Math.min(Math.pow(2,2), Math.pow(2,3)), 4, 0);
        assertEquals(Math.max(Math.pow(2,2), Math.pow(2,3)), 8, 0);

        //Math.round은 반올림한다.
        assertEquals(Math.round(4.8), 5);
    }

    @Test
    public void string() {
        //문자열은 문자의 Sequence이다.
        //\u2122은 상호기호이다.
        //Java[상호기호]를 띄우라
        System.out.println("Java\u2122");

        //+ 연산자를 이용하여 문자열 Hello Java를 띄우라
        String location = "Java";
        String greeting = "Hello " + location;
        assertEquals(greeting, "Hello Java");

        //join 메서드는 여러 문자열을 구분자로 구분해서 결합한다.
        String names = String.join(", ", "Peter", "Paul", "Mary");
        assertEquals(names, "Peter, Paul, Mary");
        System.out.println(names);

        //수 많은 문자열을 연결하는 것은 비효율 적이다. StringBuilder을 이용하라

        String[] values = names.split(", ");
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<values.length; i++) {
            builder.append(values[i]);
            if(i != values.length -1) {
                builder.append(", ");
            }
        }
        String result = builder.toString();
        assertEquals(names, result);
    }
}