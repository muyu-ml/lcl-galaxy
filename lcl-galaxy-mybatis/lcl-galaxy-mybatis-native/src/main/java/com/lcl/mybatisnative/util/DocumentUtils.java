package com.lcl.mybatisnative.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.*;

public class DocumentUtils {
    public static Document readInputStream(InputStream inputStream) {
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(inputStream);
            return document;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static  Document readInputStream1(String path){
        SAXReader saxReader=new SAXReader();
        File file=new File(path);
        Document document=null;
        InputStreamReader inputStreamReader= null;
        try {
            inputStreamReader = new InputStreamReader(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            document=saxReader.read(inputStreamReader);
            return document;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }



    public static  Document readInputStream2(String path){
        SAXReader saxReader=new SAXReader();
        File file=new File(path);
        Document document=null;
        InputStreamReader inputStreamReader= null;
        try {
            document=saxReader.read(inputStreamReader);
            return document;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
