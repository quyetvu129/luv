package com.example.luv.dto.response;

import lombok.Data;

@Data
public class CustomResponseEntity<T> {

    private int code;
    private String message;
    private T data;

    public CustomResponseEntity(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> CustomResponseEntity<T> ok(T data) {
        return new CustomResponseEntity<>(200, "Success", data);
    }

    public static <T> CustomResponseEntity<T> success() {
        return new CustomResponseEntity<>(200, "Success", null);
    }

}
