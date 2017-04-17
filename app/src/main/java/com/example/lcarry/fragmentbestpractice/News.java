package com.example.lcarry.fragmentbestpractice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ${lcarry} on 2017/4/14.
 */

public class News {
    private String title;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static List<News> getNews(){
        List<News> newsList = new ArrayList<>();
        for (int i = 0; i <50; i++) {
            News news = new News();
            news.setTitle("This is a new title" + i);
            news.setContent(getRandomLengthContent("This is a new Content" + i));
            newsList.add(news);
        }

        return newsList;
    }

    private static String getRandomLengthContent(String content){
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(content);
        }
        return builder.toString();
    }

}
