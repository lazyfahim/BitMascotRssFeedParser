package com.fahimabrar;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class RssWriter extends Thread {
    BufferedReader reader;
    public  RssWriter(){

    }
    @Override
    public void run(){
        while (true){
            try{
                reader = new BufferedReader(new FileReader("d:\\a.rss") );
                File file = new File("d:\\a.rss");
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(file);
                doc.getDocumentElement().normalize();
                NodeList nl = doc.getElementsByTagName("item");
                int length  = nl.getLength();
                for(int i=0;i<length; i++){
                    Node currentNode = nl.item(i);
                    NodeList childNodes  = currentNode.getChildNodes();
                    int itemnum = 0;
                    for(int j = 0;j< childNodes.getLength();j++){
                        String dtrn = childNodes.item(j).getNodeName();
                        if(dtrn.equals("media:group"))
                            itemnum = j;
                    }
                    Node mediaGroup = childNodes.item(itemnum);
                    NodeList links = mediaGroup.getChildNodes();
                    for(int j=0;j< links.getLength();j++) {
                        Node link = links.item(j);
                        NamedNodeMap atrib = link.getAttributes();
                        Node name = atrib.getNamedItem("type");
                        String kuci = name.getNodeValue();
                        if(kuci.equals("image/jpeg")){
                            String linkUrl = atrib.getNamedItem("url").getNodeValue();
                            System.out.println(linkUrl);
                        }
                    }
                }
                Thread.sleep(15000);
            }catch (Exception ex){
                System.out.println(ex);
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
