package com.tt.oa.controller;

import com.tt.oa.entity.ListNode;
import com.tt.oa.entity.TreeNode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ListController {
    @RequestMapping("list")
    public String tree(Model model) {
        String string = "1 2 3 4 5";
        ListNode root = new ListNode();
        ListNode first = new ListNode();
        first=root;
        String[] strings = string.split(" ");
        for (String str : strings) {
            ListNode temp=new ListNode(Integer.parseInt(str));
            root.setNext(temp);
            root=temp;
        }
        model.addAttribute("root", first);
        return "index";
    }
}
