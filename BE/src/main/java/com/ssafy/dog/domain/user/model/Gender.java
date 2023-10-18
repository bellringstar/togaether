package com.ssafy.dog.domain.user.model;

public enum Gender {
    MALE("M"),
    FEMALE("F");

    private final String code;

    Gender(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Gender fromCode(String code) {
        if (code != null) {
            for (Gender gender : values()) {
                if (gender.code.equals(code)) {
                    return gender;
                }
            }
        }
        throw new IllegalArgumentException("Invalid Gender code: " + code);
    }
}