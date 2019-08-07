package com.tt.oa.dom;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

public class Dom4jQuery {
    public static void main(String[] args) {
        try {
            File file=new File("dom.xml");
            SAXReader reader=new SAXReader();
            Document document=reader.read(file);

            System.out.println("Root element : "+document.getRootElement().getName());

            Element classElement=document.getRootElement();

            List<Node> nodes=document.selectNodes("student");
            System.out.println("===============================");
            for(Node node:nodes){

            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
