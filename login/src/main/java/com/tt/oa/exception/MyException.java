package com.tt.oa.exception;

public class MyException extends Exception {
    //无参构造函数
    public MyException() {
    }

    //有参构造函数
    public MyException(String message){
        super(message);
    }
}
