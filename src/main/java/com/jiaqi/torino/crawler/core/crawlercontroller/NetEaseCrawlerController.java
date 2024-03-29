package com.jiaqi.torino.crawler.core.crawlercontroller;

import com.jiaqi.torino.crawler.core.webcrawler.NetEaseCrawler;

import org.springframework.stereotype.Component;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

@Component
public class NetEaseCrawlerController implements CrawlerController {

    @Override
    public void startCrawler() throws Exception {
        String crawlStorageFolder = "./data/crawl/netease";
        int numberOfCrawlers = 15;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setMaxDepthOfCrawling(50);
        config.setMaxPagesToFetch(5000);


        // Instantiate the controller for this crawl.
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        // For each crawl, you need to add some seed urls. These are the first
        // URLs that are fetched and then the crawler starts following links
        // which are found in these pages
        controller.addSeed("https://news.163.com/");

    	// The factory which creates instances of crawlers.
        CrawlController.WebCrawlerFactory<NetEaseCrawler> factory = NetEaseCrawler::new;
        
        // Start the crawl. This is a blocking operation, meaning that your code
        // will reach the line after this only when crawling is finished.
        controller.start(factory, numberOfCrawlers);
    }
    
}