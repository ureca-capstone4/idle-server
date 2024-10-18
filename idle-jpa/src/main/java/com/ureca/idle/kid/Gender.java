package com.ureca.idle.kid;

import lombok.Getter;
import java.util.Arrays;

@Getter
public enum Gender {

    BOY("boy"),
    GIRL("girl");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    public static Gender from(String gender) {
        return Arrays.stream(Gender.values())
                .filter(it -> it.value.equalsIgnoreCase(gender))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("올바른 성별을 입력해주세요.")); // TODO 예외의 정확한 포맷 정하기
    }
}

