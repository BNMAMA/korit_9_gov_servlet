package com.korit.servlet_study.ch06;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalInt;

public class OptionalMain {
    public static void main(String[] args) {
        //optional은 생성 불가능
        //대신 생성하는 법
        Optional<String> stringOptional1 = Optional.empty();
        Optional<String> stringOptional2 = Optional.of("데이터"); //of에는 null을 넣을 수가 없다 -> 값을 넣어야 함
        Optional<String> stringOptional3 = Optional.ofNullable(null); // 많이 씀

        boolean flag = true;
        Optional<String> op = Optional.ofNullable(flag ? "데이터1" : null);
        System.out.println(op);

        // optional에서 값 가져오기 (null일 경우 값을 가져오지 못함 -> get 자체가 불가능)
        System.out.println(op.get());
        // orElseGet(supplier) 리턴 값만 나옴
        System.out.println(op.orElseGet(() -> "데이터"));
        System.out.println(op.orElseGet(() -> null));
        System.out.println(op.orElse("데이터3")); //대체 될 수 있는 값을 말함


        // 조건부로 값 가져오기
        System.out.println(op.isEmpty());
        System.out.println(op.isPresent());
        if (op.isPresent()) {
            System.out.println(op.get());
        } else {
            System.out.println("null");
        }
        // 조건부 + Optional
        op.ifPresent(value -> System.out.println("값이 있으면" + value));
        op.ifPresentOrElse(
                value -> System.out.println("값이 있으면" + value),
                () -> System.out.println("값이 없어서 이거 실행됨")
        );
        try {
            String data = op.orElseThrow();
            System.out.println("예외 안터지고 실행됨: data");
        } catch (NoSuchElementException e) {
            System.out.println("예외 터짐");
        }

        try {
            String data = op.orElseThrow(() -> new RuntimeException("내가 생성한 예외"));

        } catch (NoSuchElementException e) {

        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("이쪽으로 예외 처리함");
        }


    }
}
