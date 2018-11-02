package com.heyu.spider.page.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class HttpUtil {

    /**
     * get请求，不带参数
     * @param url
     * @return
     */
    public static String doGet(String url){
        return doGet(url, Collections.EMPTY_MAP,Collections.EMPTY_MAP);
    }

    /**
     * get请求，带URL参数
     * @param url
     * @param params
     * @return
     */
    public static String doGet(String url,Map<String,Object> params){
        return doGet(url,Collections.EMPTY_MAP,params);
    }

    /**
     * 发送GET请求方法
     * @param url 请求路径
     * @param header 请求头
     * @param params 请求参数
     * @return
     */
    public static String doGet(String url,Map<String,String> header, Map<String,Object> params){
        if(StringUtils.isEmpty(url)){
            return null;
        }

        String result = "";
        try {
            String apiUrl = getUrlWithParams(url,params);
            HttpGet httpGet = new HttpGet(apiUrl);
            if(header != null){
                for (Map.Entry<String,String> entry:header.entrySet()){
                    httpGet.addHeader(entry.getKey(),entry.getValue());
                }
            }

            HttpClient httpClient = HttpClients.createDefault();
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity responseEntity = null;
            if(response != null && response.getStatusLine().getStatusCode() == 200){
                responseEntity = response.getEntity();
            }
            result = EntityUtils.toString(responseEntity,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * URL和URL参数拼接
     * @param url 请求地址
     * @param params 参数
     * @return
     */
    private static String getUrlWithParams(String url,Map<String,Object> params){
        URIBuilder uriBuilder = null;
        try {
            uriBuilder = new URIBuilder(url);
            if(params != null && params.size() != 0){
                for(Map.Entry<String,Object> entry:params.entrySet()){
                    uriBuilder.addParameter(entry.getKey(),entry.getValue().toString());
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return uriBuilder.toString();
    }

    /**
     * post请求
     * @param url 链接
     * @return
     */
    public static String doPost(String url){
        return doPost(url,null,null,null);
    }

    /**
     * post请求，表单参数
     * @param url 链接
     * @param formData 表单参数
     * @return
     */
    public static String doPost(String url,Map<String,Object> formData){
        return doPost(url,null,formData,null);
    }

    /**
     * post请求，json请求体
     * @param url 链接
     * @param jsonParams json字符串
     * @return
     */
    public static String doPost(String url, String jsonParams){
        return doPost(url,null,null,jsonParams);
    }

    /**
     * 发送post请求方法
     * @param url 请求路径
     * @param header 请求头
     * @param formData 表单提交参数
     * @param jsonParams json提交参数
     * @return
     */
    public static String doPost(String url, Map<String,String> header, Map<String,Object> formData, String jsonParams){
        if(StringUtils.isEmpty(url)){
            return null;
        }

        String result = "";

        try {
            HttpPost httpPost = new HttpPost(url);
            if(header != null){
                for(Map.Entry<String,String> entry:header.entrySet()){
                    httpPost.addHeader(entry.getKey(),entry.getValue());
                }
            }

            //设置请求体，不能既设置了表单提交，又设置请求体提交参数
            if(jsonParams != null){
                httpPost.setEntity(new StringEntity(jsonParams,"UTF-8"));
                httpPost.setHeader("Content-Type", "application/json");
            } else if(formData != null){
                httpPost.setEntity(getUrlEncodedFormEntity(formData));
                httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            }

            HttpClient httpClient = HttpClients.createDefault();
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = null;
            if(response != null && response.getStatusLine().getStatusCode() == 200){
                responseEntity = response.getEntity();
            }
            result = EntityUtils.toString(responseEntity,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static HttpEntity getUrlEncodedFormEntity(Map<String,Object> params){
        try {
            List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
            if(params != null) {
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    BasicNameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
                    pairList.add(pair);
                }
            }
            return new UrlEncodedFormEntity(pairList,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
