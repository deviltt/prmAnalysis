package com.tt.oa.controller;

import com.tt.oa.entity.ZTreeNode;
import com.tt.oa.io.FileReaderTest;
import com.tt.oa.util.PartialModificationWithNIO;
import com.tt.oa.util.TraversAndCount;
import org.apache.ibatis.annotations.Param;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.tt.oa.util.BuildZTree.buildZTree;

@Controller
@RequestMapping("/ztree/")
public class ZTreeController {
    private ZTreeNode tempRoot = new ZTreeNode();
    String fileName;

    @RequestMapping(value = "build", method = RequestMethod.POST)
    @ResponseBody
    public String tree(@Param("uploadFile") MultipartFile uploadFile) throws IOException {
        fileName = uploadFile.getOriginalFilename();
        ZTreeNode root = new ZTreeNode();
        List<Map<String, List<String>>> list = FileReaderTest.traverseRoot(FileReaderTest.getZTreeString(uploadFile.getInputStream(), root));
        //迭代构建n叉树
        root.setOpen(true);
        root.setChildren(buildZTree(list, 1));
        tempRoot = root;
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

    @RequestMapping("update")
    @ResponseBody
    public Map<String, String> updateAttribute(@RequestBody List<Map<String, String>> mapList) throws IOException {
        Map<String, String> modelMap = new HashMap<>();
        for (Map<String, String> map : mapList) {
            //计算位置
            TraversAndCount traversAndCount = new TraversAndCount(23);
            int count = 0, depth = 0;
            String key = null;
            Map<String, String> resultMap = new LinkedHashMap<>();    //LinkedHashMap按照插入的顺序排列
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if ("count".equals(entry.getKey())) {
                    count = Integer.parseInt(entry.getValue());
                } else if ("depth".equals(entry.getKey())) {
                    depth = Integer.parseInt(entry.getValue());
                } else if ("key".equals(entry.getKey())) {
                    key = entry.getValue();
                } else {
                    resultMap.put(entry.getKey(), entry.getValue());
                }
            }
            System.out.println(count + " " + key + " " + depth);
            traversAndCount.countZTreePosition(count, key, tempRoot.getChildren());
            int position = traversAndCount.getPosition();
            System.out.println(position);
            PartialModificationWithNIO.changeZTreeTxt(map, position, count, depth - 1, key, tempRoot.getChildren());
            //修改完文件后，需要重新跟新一下树结构
            tempRoot = new ZTreeNode();

            File file = new File("D:\\prm\\login\\file\\" + fileName);
            System.out.println("D:\\prm\\login\\file\\" + fileName);
            FileInputStream fileInputStream = new FileInputStream(file);

            MultipartFile uploadFile = new MockMultipartFile(file.getName(), fileInputStream);

            List<Map<String, List<String>>> list = FileReaderTest.traverseRoot(FileReaderTest.getZTreeString(uploadFile.getInputStream(), tempRoot));
            //迭代构建n叉树
            tempRoot.setOpen(true);
            tempRoot.setChildren(buildZTree(list, 1));
        }
        modelMap.put("msg", "success");
        return modelMap;
    }
}
