package com.zhuhong.inspection.base;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

public class Result<T>  implements Serializable {

    private static final long serialVersionUID = 1L;
    private int code;
    private String msg;
    private T data;

    public Result setCode(ResultCode resultCode) {
        this.code = resultCode.code();
        return this;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static Result genSuccessResult() {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMsg(DEFAULT_SUCCESS_MESSAGE);
    }

    public static <T> Result<T> genSuccessResult(T data) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMsg(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static Result genSuccessResultMsg(String message) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMsg(message);
    }

    public static <T> Result<T> genSuccessResult(T data, String message) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMsg(message)
                .setData(data);
    }

    public static Result genFailResult(String message) {
        return new Result()
                .setCode(ResultCode.FAIL)
                .setMsg(message);
    }

    public static <T> Result<T> genResult(ResultCode resultCode, String message, T data) {
        return new Result()
                .setCode(resultCode)
                .setMsg(message)
                .setData(data);
    }

    public static Result error(ExceptionEnum exceptionEnum) {
        Result result = new Result();
        result.setCode(exceptionEnum.getCode());
        result.setMsg(exceptionEnum.getMsg());
        result.setData(null);
        return result;
    }

}
