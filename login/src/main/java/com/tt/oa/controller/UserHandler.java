package com.tt.oa.controller;

import com.tt.oa.entity.User;
import com.tt.oa.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("login")
public class UserHandler {
    private static Logger logger=LoggerFactory.getLogger(UserHandler.class);

    @Autowired
    private UserService userService;

    //跳转初始登录页面的路由
    @RequestMapping("toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("doLogin")
    public String index(User user, Model model,HttpServletRequest request){ //这边使用了springMVC的数据绑定功能
        User user1=userService.getUser(user.getUsername());
        String errMsg;
        if (user1==null){
            errMsg="该用户未注册...";
            model.addAttribute("errMsg", errMsg);
            model.addAttribute("register", false);
            logger.info(errMsg);
            return "login";
        }else if (!user.getPassword().equals(user1.getPassword())){
            errMsg="密码不正确，请重新输入...";
            model.addAttribute("password", false);
            model.addAttribute("errMsg", errMsg);
            logger.info(errMsg);
            return "login";
        }
        request.getSession().setAttribute("user", user1);
        return "redirect:/result/success";
    }
}
