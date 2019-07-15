package com.jiaqi.torino.crawler.core.parser;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.jiaqi.torino.model.to.NewsDTO;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class NetEaseNewsParser implements NewsParser {

    public static String source = "NetEase";

    @Override
    public NewsDTO parseNews(Document doc) {
        String titleSelector = "head > title";
        String timeSelector = "head > meta[property = article:published_time]";
        Elements title = doc.select(titleSelector);
        if (title.first() != null) {
            NewsDTO news = new NewsDTO();
            news.setTitle(title.first().text());
            news.setCreateTime(ZonedDateTime.now());
            news.setSource(source);
            Elements time = doc.select(timeSelector);
            if (time.first() != null) {
                String publishTime = time.first().attr("content");
                news.setPublishTime(ZonedDateTime.parse(publishTime));
            }
            return news;
        }
        return null;
    }
}