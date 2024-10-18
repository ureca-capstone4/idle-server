package com.ureca.idle.bookpreference;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Hobulho {

    HO("ho"),
    BULHO("bulho");

    private final String value;

    Hobulho(String value) {
        this.value = value;
    }

    public static Hobulho from(String hobulho) {
        return Arrays.stream(Hobulho.values())
                .filter(it -> it.value.equalsIgnoreCase(hobulho))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("give me ho or bulho.")); // TODO 예외의 정확한 포맷 정하기
    }
}
