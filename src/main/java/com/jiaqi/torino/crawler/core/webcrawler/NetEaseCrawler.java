package com.jiaqi.torino.crawler.core.webcrawler;

import java.util.Set;
import java.util.regex.Pattern;

import com.jiaqi.torino.crawler.core.parser.NetEaseNewsParser;
import com.jiaqi.torino.model.to.NewsDTO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class NetEaseCrawler extends WebCrawler {
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
            + "|png|mp3|mp4|zip|gz))$");
    
    private final static String BASE_URL = "news.163.com/";

    private final static Pattern NEWS_PAGE_FILTER = Pattern.compile(
            "(http|https)://news.163.com/\\d+/\\d+/\\d+/.+");
    
    @Override
    public boolean shouldVisit(Page page, WebURL url) {
        String webUrl = url.getURL().toLowerCase();
        if (FILTERS.matcher(webUrl).matches()) {
            return false;
        }
        if (!webUrl.contains(BASE_URL)) {
            return false;
        }
        return true;
    }

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        System.out.println("URL: " + url);

        if (NEWS_PAGE_FILTER.matcher(url).matches()) {
            if (page.getParseData() instanceof HtmlParseData) {
                HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
                Document doc = Jsoup.parse(htmlParseData.getHtml());
                NewsDTO news = new NetEaseNewsParser().parseNews(doc);
                System.out.println(String.format("%s %s %s %s",
                        news.getTitle(),
                        news.getPublishTime(),
                        news.getSource(),
                        news.getUrl()));
            }
        }
    }
}