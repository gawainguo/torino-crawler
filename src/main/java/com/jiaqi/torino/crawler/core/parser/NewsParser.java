package com.jiaqi.torino.crawler.core.parser;

import com.jiaqi.torino.model.to.NewsDTO;

import org.jsoup.nodes.Document;

public interface NewsParser {

    NewsDTO parseNews(Document doc);
}