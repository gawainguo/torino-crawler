package com.jiaqi.torino.crawler.controller;

import com.jiaqi.torino.crawler.core.crawlercontroller.NetEaseCrawlerController;
import com.jiaqi.torino.model.resp.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private NetEaseCrawlerController netEaseController;

    @GetMapping("/start/netease")
    public Response startNeteaseCrawler() {
        try {
            netEaseController.startCrawler();
            return Response.success();
        } catch (Exception e) {
            return Response.error().withMsg(e.getMessage());
        }
    }
}