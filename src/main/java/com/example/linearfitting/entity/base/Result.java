package com.example.linearfitting.entity.base;

import lombok.Data;

/**
 * @author
 */
@Data
public class Result<T> {
    private int code;
    private String message;
    private T data;

    public Result() {
        this.code = 0;
        this.message = "成功";
    }

//******************************** Result method *********************************//

    public static final Result<String> success() {
        return success(null);
    }

    public static <T> Result<T> build(int code, String message, T data) {
        Result<T> result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static final <T> Result<T> success(T t) {
        Result result = new Result();
        result.setCode(0);
        result.setMessage("成功");
        result.setData(t);
        return result;
    }

    public static final <T> Result<T> error(String message) {
        return error(1, message);
    }

    public static final <T> Result<T> error(int code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}
