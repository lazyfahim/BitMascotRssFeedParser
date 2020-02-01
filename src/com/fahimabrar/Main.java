package com.fahimabrar;

public class Main {

    public static void main(String[] args) {
        RssReader rssReader = new RssReader();
        RssWriter rssWriter = new RssWriter();
        rssReader.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        rssWriter.start();
    }
}
