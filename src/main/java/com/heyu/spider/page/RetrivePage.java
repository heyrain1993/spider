package com.heyu.spider.page;


import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RetrivePage {

    private static HttpClient httpClient = HttpClients.createDefault();

    private static RequestConfig requestConfig = null;

    /**
     * 设置代理和端口
     */
    static {
        HttpHost httpHost = new HttpHost("172.16.11.174",8080);
        requestConfig = RequestConfig.custom().setProxy(httpHost).build();
    }

    public static void main(String[] args) {

    }

    public static Boolean downloadPage(String path){

        HttpResponse response = null;
        InputStream input = null;
        OutputStream outputStream = null;
        try {

            HttpPost httpPost = new HttpPost(path);
            List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("username","zhangsan"));
            nameValuePairs.add(new BasicNameValuePair("password","123456");
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response = httpClient.execute(httpPost);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                outputStream = new FileOutputStream("index.html");
                byte[] bytes = new byte[1024];
                if(input.read(bytes) > 0){
                    outputStream.write(bytes);
                }
            }
            httpPost.releaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(input != null){
                    input.close();
                }
                if(outputStream != null){
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
