package com.module.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.module.pojo.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MusicUtil {
    public static void main(String[] args) {
        String url = "http://service.5sing.kugou.com/mv/listNew?jsoncallback=jQuery1703316953231266626_1647179362095&from=web&type=1&sortType=3&pageIndex=1&pageSize=4&kind=0&_=1647179362129&_=1647179362129";
        String s = CrawlerUtils.getIndexHtml(url);
        s = s.substring(40);
        s = s.substring(0, s.length() - 1);
        s = unicodeToString(s);
        System.out.println("s = " + s);
        System.out.println("jQuery1703316953231266626_1647179362095(".length());
        JSONObject a1 = JSON.parseObject(s);
        JSONArray arr = a1.getJSONObject("data").getJSONArray("list");
        for (Object o : arr) {
            Article article=new Article();
            JSONObject obj=(JSONObject)o;
            String cover_url = obj.getString("cover_url");
            System.out.println("cover_url = " + cover_url);
            article.setPicurl(cover_url);
            article.setTitle(obj.getString("title"));
            article.setContent(obj.getString("intro"));
        }

    }

    public static String unicodeToString(String str) {

        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch + "");
        }
        return str;
    }
}
