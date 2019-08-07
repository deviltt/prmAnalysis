package com.tt.oa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("result")
public class SuccessHandler {
    @RequestMapping("success")
    public String toSuccess(){
        return "success";
    }
}
