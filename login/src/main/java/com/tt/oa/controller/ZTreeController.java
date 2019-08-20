package com.tt.oa.controller;

import com.tt.oa.entity.ZTreeNode;
import com.tt.oa.io.FileReaderTest;
import com.tt.oa.util.BuildTrie;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.tt.oa.util.BuildZTree.buildZTree;

@Controller
@RequestMapping("/ztree/")
public class ZTreeController {
    @RequestMapping(value = "build", method = RequestMethod.POST)
    @ResponseBody
    public String tree(@Param("uploadFile") MultipartFile uploadFile) throws IOException {
        ZTreeNode root = new ZTreeNode();
        List<Map<String, List<String>>> list = FileReaderTest.traverseRoot(FileReaderTest.getZTreeString(uploadFile.getInputStream(), root));
        //迭代构建n叉树
        root.setOpen(true);
        root.setChildren(buildZTree(list));
        //这里是放在session里面的，但可能文件太大了会影响性能
//        request.getSession().setAttribute("result", ZTreePreOrderTraverse(root.getChildren(), 0, stringBuilder));
        //构建trie，需要遍历n叉树的每一个节点，以及节点的List<Map<String, String>>集合
//        BuildTrie.buildTrie(root, trie);
//        System.out.println(trie);
        return root.toString();
    }

    @RequestMapping("toUpload")
    public String toUpload() {
        return "upload";
    }

    @RequestMapping("toZTree")
    public String toZTree() {
        return "ztree";
    }
}
