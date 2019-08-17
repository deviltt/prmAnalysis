package com.tt.oa.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@ControllerAdvice
public class MyExceptionHandler2 {
    @ExceptionHandler(value = {MyException.class, SQLException.class, RuntimeException.class})
    public String myExceptionHanler(HttpServletRequest request, Exception ex) {
        request.setAttribute("ex", ex);
        System.out.println(ex.getClass().getSimpleName());

        return "/exception/db-error";
    }

//    @ExceptionHandler(value = SQLException.class)
//    public String dbException(HttpServletRequest request, Exception ex) {
//        request.setAttribute("ex", ex);
//        System.out.println(ex.getClass().getSimpleName());
//        System.out.println("db");
//        return "/exception/db-error";
//    }
}
