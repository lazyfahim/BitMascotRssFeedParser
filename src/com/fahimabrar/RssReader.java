package com.fahimabrar;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class RssReader extends Thread{
    static URL url ;
    public RssReader(){
        try {
            this.url  = new URL("http://rss.cnn.com/rss/edition.rss");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public void run(){
        while (true){
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                FileOutputStream writer = new FileOutputStream("d:\\a.rss");
                String rssString = "";
                String tempStr = "";
                while ((tempStr = reader.readLine()) != null) {
                    byte data[] = tempStr.getBytes();
                    writer.write(data);
                }
                writer.close();
                reader.close();
                Thread.sleep(15000);

            }catch (Exception ex){
                System.out.println(ex);
            }
        }
    }

}
