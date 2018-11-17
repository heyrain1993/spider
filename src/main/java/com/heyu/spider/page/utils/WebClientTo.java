package com.heyu.spider.page.utils;

import com.gargoylesoftware.htmlunit.WebClient;

public class WebClientTo extends WebClient{

    private int timeout;

    public WebClientTo(int timeout){
        this.timeout = timeout;
    }


}
