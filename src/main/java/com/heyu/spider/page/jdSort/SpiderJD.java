package com.heyu.spider.page.jdSort;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.heyu.spider.page.jdSort.dao.DictDao;
import com.heyu.spider.page.jdSort.dao.DictFiledDao;
import com.heyu.spider.page.jdSort.dao.DictTempDao;
import com.heyu.spider.page.jdSort.dao.TitleDao;
import com.heyu.spider.page.jdSort.entity.Dict;
import com.heyu.spider.page.jdSort.entity.DictFiled;
import com.heyu.spider.page.jdSort.entity.Title;
import com.heyu.spider.page.utils.HttpUtil;
import com.heyu.spider.page.utils.SHA256Util;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class SpiderJD {

    @Autowired
    private DictDao dictDao;

    @Autowired
    private DictFiledDao dictFiledDao;

    @Autowired
    private DictTempDao dictTempDao;

    private static WebClient webClient;

    /**
     * 爬取京东首页所有的list.html链接
     * 插入到t_dict表中
     */
    public void spiderFirstPage() throws IOException {
        File file = new File("F:\\爬虫\\jd_index.html");
        Document jdDoc = Jsoup.parse(file,"utf-8");
        Elements elements = jdDoc.select("a");
        List<Dict> dicts = new ArrayList<>();
        for(Element element:elements){
            String href = element.attr("href");
            if(href.contains("list.jd.com/list.html")){
                Dict dict = new Dict();
                dict.setUrl(href);
                dict.setCreateTime(new Date());
                dicts.add(dict);
            }
        }
        dictDao.insertBatch(dicts);
    }

    /**
     * 遍历所有的list.html
     * 并爬取每个HTML中的list.html，并去重复
     * @throws IOException
     */
    public void spiderFirstPage1() throws IOException {
        //获取首页所有的list.html
        List<Dict> dicts = dictDao.findAllList();
        //用于存放新的list.html
        Set<String> dictsTempStr = new HashSet<>();
        List<Dict> dictsTemp = new ArrayList<>();
        for (Dict dict : dicts) {
            Document jdDoc = Jsoup.connect("https:" + dict.getUrl()).get();
            Elements elements = jdDoc.select("a");
            for (Element element : elements) {
                String href = element.attr("href");
                if (href.contains("/list.html?cat=") && href.length() < 100) {
                    if(href.contains("&")){
                        href = href.substring(0,href.indexOf("&"));
                    }
                    dictsTempStr.add(href);
                    System.out.println(href);
                }
            }
        }
        for(String str:dictsTempStr){
            Dict temp = new Dict();
            temp.setUrl(str);
            temp.setCreateTime(new Date());
            dictsTemp.add(temp);
        }
        if(dictsTemp != null && dictsTemp.size() != 0){
            dictTempDao.insertBatch(dictsTemp);
        }
    }

    private HashMap<String, Title> titles = new HashMap<>();
    @Autowired
    private TitleDao titleDao;
    public void spiderTitle() {
        List<Dict> dicts = dictTempDao.findAllList();
        for (Dict dict : dicts) {
            //parseBrand(dict);
            try {
                parseFiled(dict);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
        Collection<Title> collections = titles.values();
        titleDao.insertBatch(new ArrayList<Title>(collections));
    }

    /**
     * 爬取list.html中被激活的目录
     * @param dict
     * @throws IOException
     */
    @Async("threadPoolA")
    public void parseFiled(Dict dict) throws IOException {
        Document jdDoc = Jsoup.connect("https://list.jd.com" + dict.getUrl()).get();
        System.out.println("解析三级目录...");
        Elements crumbsNavItem = jdDoc.select(".crumbs-nav-item");
        if (crumbsNavItem == null || crumbsNavItem.size() == 0) {
            return;
        }
        //构建一级目录
        Element element0 = crumbsNavItem.get(0);
        String firstTitle = element0.select("a").text();
        //构建二级目录
        Element element1 = crumbsNavItem.get(1);
        String triggerSecondTitle = element1.select(".trigger").select("span").text();

        Element element2 = null;
        String triggerThirdTitle = "";
        if (crumbsNavItem.size() == 3) {
            element2 = crumbsNavItem.get(2);
            triggerThirdTitle = element2.select(".trigger").select("span").text();
            //Elements li = element2.select("ul").select("li");
            Title title = new Title();
            title.setDictId(dict.getId());
            title.setFirstTitle(firstTitle);
            title.setSecondTitle(triggerSecondTitle);
            title.setThridTitle(triggerThirdTitle);
            title.setUrl(dict.getUrl());
            title.setCreateTime(new Date());
            titles.put(dict.getUrl(), title);
            /*for(Element element:li){
                String a = element.select("a").text();
                String url = element.select("a").attr("href");
                Title title = new Title();
                title.setDictId(dict.getId());
                title.setFirstTitle(firstTitle);
                title.setSecondTitle(triggerSecondTitle);
                title.setThridTitle(a);
                title.setUrl(url);
                title.setCreateTime(new Date());
                String hashcode = SHA256Util.getSHA256StrJava(firstTitle+triggerSecondTitle+a);
                titles.put(hashcode,title);
            }*/

        } else {
            //Elements li = element1.select("ul").select("li");
            Title title = new Title();
            title.setDictId(dict.getId());
            title.setFirstTitle(firstTitle);
            title.setSecondTitle(triggerSecondTitle);
            title.setThridTitle(triggerThirdTitle);
            title.setUrl(dict.getUrl());
            title.setCreateTime(new Date());
            titles.put(dict.getUrl(), title);
            /*for(Element element:li){
                String a = element.select("a").text();
                String url = element.select("a").attr("href");
                Title title = new Title();
                title.setDictId(dict.getId());
                title.setFirstTitle(firstTitle);
                title.setSecondTitle(a);
                title.setUrl(url);
                title.setCreateTime(new Date());
                String hashcode = SHA256Util.getSHA256StrJava(firstTitle+a);
                titles.put(hashcode,title);
            }*/
        }
    }

    /**
     * 抓取选项
     */
    public void spiderOption() {
        List<Dict> dicts = dictTempDao.findAllList();
        for (Dict dict : dicts) {
            try {
                parseOption(dict);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    /**
     * 解析选项
     * @param dict
     * @throws IOException
     */
    @Async("threadPoolA")
    public void parseOption(Dict dict) throws IOException {
        List<DictFiled> dictFileds = new ArrayList<>();
        Document jdDoc = getDoc("https://list.jd.com" + dict.getUrl());
        Elements crumbsNavItem = jdDoc.select(".crumbs-nav-item");
        String triggerThirdTitle = "";
        if (crumbsNavItem.size() == 3) {
            Element element2 = crumbsNavItem.get(2);
            triggerThirdTitle = element2.select(".trigger").select("span").text();
        } else {
            Element element1 = crumbsNavItem.get(1);
            triggerThirdTitle = element1.select(".trigger").select("span").text();
        }
        Elements selectorLines = jdDoc.select(".J_selectorLine");
        for(Element selectorLine:selectorLines){
            String filed = selectorLine.select(".sl-key").select("span").text();
            Elements selectorValues = selectorLine.select(".sl-value").select(".sl-v-list").select("li");
            for(Element selectorValue:selectorValues){
                String filedValue = selectorValue.text();
                DictFiled dictFiled = new DictFiled();
                dictFiled.setDictId(dict.getId());
                dictFiled.setDictName(triggerThirdTitle);
                dictFiled.setFiled(filed);
                dictFiled.setFiledValue(filedValue);
                dictFileds.add(dictFiled);
            }
        }
        if(dictFileds == null || dictFileds.size() == 0){
            return;
        }
        dictFiledDao.insertBatch(dictFileds);
    }

    @Async("threadPoolA")
    public void parseBrand(Dict dict) {
        String json = HttpUtil.doGet("https:" + dict.getUrl() + "&sort=sort_rank_asc&trans=1&md=1&my=list_brand");
        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.parseObject(json);
        } catch (Exception e) {
            //e.printStackTrace();
            return;
        }
        if (jsonObject == null) {
            return;
        }
        JSONArray brands = jsonObject.getJSONArray("brands");
        if (brands == null || brands.size() == 0) {
            return;
        }
        List<DictFiled> dictFileds = new ArrayList<>();
        for (int i = 0; i < brands.size(); i++) {
            JSONObject brand = brands.getJSONObject(i);

            DictFiled dictFiled = new DictFiled();
            dictFiled.setDictId(dict.getId());
            dictFiled.setFiled("品牌");
            dictFiled.setFiledValue(brand.getString("name"));
            dictFileds.add(dictFiled);

        }

        dictFiledDao.insertBatch(dictFileds);
    }

    public static Document getDoc(String url) throws IOException {
        WebClient webClient = getWebClient();
        HtmlPage page = null;

        page = webClient.getPage(url);

        return Jsoup.parse(page.asXml());
    }

    public static WebClient getWebClient() {
        if (webClient != null) {
            return webClient;
        }
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setThrowExceptionOnScriptError(false);//当JS执行出错的时候是否抛出异常, 这里选择不需要
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);//当HTTP的状态非200时是否抛出异常, 这里选择不需要
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);//是否启用CSS, 因为不需要展现页面, 所以不需要启用
        webClient.getOptions().setJavaScriptEnabled(true); //很重要，启用JS
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//很重要，设置支持AJAX

        //System.out.println(" //等待JS驱动dom完成获得还原后的网页 ");
        webClient.waitForBackgroundJavaScript(10000);
        return webClient;
        //System.out.println("  网页内容 ");
        //System.out.println( page.asXml());
    }


}
