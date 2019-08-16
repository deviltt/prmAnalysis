package com.tt.oa.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MyExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Map<String, Object> model = new HashMap<>();
        //把异常类放进map中去
        model.put("ex", ex);
        if (ex instanceof MyException) {
            return new ModelAndView("exception/my-error", model);
        } else if (ex instanceof SQLException) {
            return new ModelAndView("exception/db-error", model);
        } else {
            return new ModelAndView("exception/error", model);
        }
    }
}
