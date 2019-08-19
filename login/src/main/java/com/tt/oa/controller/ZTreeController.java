package com.tt.oa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("ztree")
public class ZTreeController {
    @RequestMapping("/toZTree")
    public String toZTree() {
        return "ztree";
    }
}
