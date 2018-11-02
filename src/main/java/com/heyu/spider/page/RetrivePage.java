package com.heyu.spider.page;

import com.alibaba.fastjson.JSON;
import com.heyu.spider.page.utils.HttpUtil;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.util.resources.CurrencyNames;

import java.text.SimpleDateFormat;
import java.util.*;

public class RetrivePage {

    private static final String URL = "http://srh.bankofchina.com/search/whpj/search.jsp";

    public static void main(String[] args) {

        //获取所有币种
        String firstPage = HttpUtil.doGet(URL,null);
        Document document = Jsoup.parse(firstPage);
        Elements elements = document.select("select#pjname option");
        Map<String,String> moneyType = new HashMap<String,String>();
        for(Element element:elements){
            moneyType.put(element.attr("value"),element.text());
        }
        System.out.println(JSON.toJSONString(moneyType));

        //设置查询参数
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date erectDate = new Date();
        Date nothing = new Date();
        String page = "1";

        //遍历币种，查询当天的汇率
        Map<String,String> prices = new HashMap<String, String>(moneyType.size());
        for(Map.Entry<String,String> entry:moneyType.entrySet()){
            Map<String,Object> formData = new HashMap<String, Object>();
            formData.put("erectDate",simpleDateFormat.format(erectDate));
            formData.put("nothing",simpleDateFormat.format(nothing));
            formData.put("page",page);
            formData.put("pjname",entry.getKey());
            String secondPage = HttpUtil.doPost("http://srh.bankofchina.com/search/whpj/search.jsp",formData);
            Document doc = Jsoup.parse(secondPage);
            //遍历tr
            Elements trs = doc.select("table tr");
            //遍历该第二行tr下的td
            Element tr = trs.get(2);
            Elements tds = tr.select("td");
            if(tds != null && tds.size() != 0){
                String price = tds.get(tds.size()-2).text();
                prices.put(entry.getValue(),price);
            }
        }

        System.out.println(JSON.toJSONString(prices));

        //Set<Currency> set = CurrencyNames.;
    }



}
