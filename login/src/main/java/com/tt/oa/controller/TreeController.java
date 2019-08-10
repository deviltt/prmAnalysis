package com.tt.oa.controller;

import com.tt.oa.entity.TreeNode;
import com.tt.oa.entity.Trie;
import com.tt.oa.io.FileReaderTest;
import com.tt.oa.util.BuildTrie;
import com.tt.oa.util.PartialModificationWithNIO;
import com.tt.oa.util.TraversAndCount;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

import static com.tt.oa.util.BuildTree.*;
import static com.tt.oa.util.BuildTrie.searchTrie;

@Controller
@RequestMapping("/tree")
public class TreeController {
    private TreeNode root = new TreeNode();
    //Trie树的根节点
    private Trie trie = new Trie();

    @RequestMapping("/toUpload")
    public String toUpload() {
        return "upload";
    }

    @RequestMapping(value = "/build", method = RequestMethod.POST)
    public String tree(@Param("uploadFile") MultipartFile uploadFile, HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        List<Map<String, List<String>>> list = FileReaderTest.traverseRoot(FileReaderTest.getString(uploadFile.getInputStream()));
        //迭代构建n叉树
        root.setNext(buildTree(list));
        //这里是放在session里面的，但可能文件太大了会影响性能
        request.getSession().setAttribute("result", preOrderTraverse(root.getNext(), 0, stringBuilder));
        //构建trie，需要遍历n叉树的每一个节点，以及节点的List<Map<String, String>>集合
        BuildTrie.buildTrie(root, trie);
//        System.out.println(trie);
        return "redirect:toIndex";
    }

    @RequestMapping("toIndex")
    public String toIndex() {
        return "index";
    }

    @RequestMapping("toShowElement")
    public String toSearch() {
        return "showElement";
    }

    /**
     * 如果查找的是属性节点，则返回属性节点，如果查找的是根节点
     */
    @RequestMapping("/search")
    public String search(@Param("key") String key, HttpServletRequest request) {
        List<TreeNode> list = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        doSearch(root, key.toLowerCase(), list);
        request.getSession().setAttribute("list", list);
//        String content = preOrderTraverse(list, 0, stringBuilder);
        String content = preOrderTraverseWithSubmit(list, 0, stringBuilder);
        //这里是放在session里面的，但可能文件太大了会影响性能
        request.getSession().setAttribute("content", content);

        return "redirect:toShowElement";
    }

    /**
     * name属性必须和ajax传递的参数名严格一致
     * 现在唯一的问题就是trie树怎么保存，不用每一次都构建trie树吧？？？？
     * 只需要构建一次，然后拿到trie树的根节点即可!
     * 每输入一个单词，就把在Trie树中与input输入框中的字符串匹配的这一路都输出
     * 不用一路都输出，输出前10个即可
     *
     * @param name name为你需要在trie树中查找的内容
     * @return
     */
    @RequestMapping("/searchTrie")
    @ResponseBody
    public Map<String, String> searchTrieMap(@Param("name") String name) {
        Map<String, String> map = new HashMap<>();
        //遍历Trie树
        searchTrie(name, trie, map);
        //根据input实时输入的内容来遍历trie树，把符合条件的单词全部列出来，或者列出前几个
        return map;
    }

    @RequestMapping("/update")
    public String updateAttribute() throws IOException {
        //计算位置
        TraversAndCount traversAndCount = new TraversAndCount(23);

        traversAndCount.countPosition(0, "ipforward", root.getNext());
        Map<String, String> map = new LinkedHashMap<>();    //LinkedHashMap按照插入的顺序排列
//        map.put("ipForwarding", "forwarding");
//        map.put("ipDefaultTTL", "65");
        int position = traversAndCount.getPosition();
        PartialModificationWithNIO.changeTxt(map, position, 0, 2, "ipforward", root.getNext());
        return "redirect:toIndex";
    }
}
