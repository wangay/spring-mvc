package com.way.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

public class HttpClientUtil {

	private final static String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws Exception {

//    	HttpClientUtil http = new HttpClientUtil();
//
//        System.out.println("Testing 1 - Send Http GET request");
//        http.sendGetAndReturnJSON(getReceiveUrl());
        
    }
    
    public static String getBlockChainAddress() throws UnsupportedEncodingException, Exception{
    	return sendGetAndReturnJSON(getReceiveUrl());
    }

    public static String getBlockChainBalance() throws UnsupportedEncodingException, Exception{
        return sendGetAndReturnJSON(getBalanceUrl());
    }



 // HTTP GET请求
    //返回json
    public static String sendGetAndReturnJSON(String url) throws Exception {
    	
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);

        //添加请求头
        request.addHeader("User-Agent", USER_AGENT);


        HttpResponse response = client.execute(request);

        System.out.println("Response Code : " +
                       response.getStatusLine().getStatusCode());

        if(!"200".equals(""+response.getStatusLine().getStatusCode())){
            System.out.println("没有取到对方的比特币地址");
            return "";
        }
        BufferedReader rd = new BufferedReader(
                       new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        
        //变成json格式，方便取值

        System.out.println(result.toString());
        JSONObject jo = new JSONObject(result.toString());
        return jo.getString("address");

    }
    
    static String getReceiveUrl() throws UnsupportedEncodingException{
    	StringBuffer urlStringBuffer = new StringBuffer();
        String url = "https://api.blockchain.info/v2/receive";
        String apiKey = "b0bdf0cd-b041-4a06-939c-b8cc2880af99";
        String xpub = "xpub6DENvF2FEzytYeivQAYUR6sZeuUCV7Tirczp5xuuuXcWY7VH27uwW9Jtqe3QNFFnJpUXMbo79QA5tWobDa5Aymoo6X9gMvYRqmrpb2CZvje";
        String callback = "http://67.216.219.13:8080";
        
        String callbackEncode = URLEncoder.encode(callback, "utf-8");
        
        urlStringBuffer.append(url).append("?key=").append(apiKey)
        			.append("&xpub=").append(xpub).append("&callback=").append(callbackEncode);
  	  

        return urlStringBuffer.toString();
    }

    /***
     * https://api.blockchain.info/v2/receive/balance_update  拼接这个url
     * @return
     * @throws UnsupportedEncodingException
     */
    static String getBalanceUrl() throws UnsupportedEncodingException{
        StringBuffer urlStringBuffer = new StringBuffer();
        String url = "https://api.blockchain.info/v2/receive/balance_update";
        String address = "1CmJdau9hmvHQgkw5ZhTq1zgTXJgxQEv64";//要查询的比特币地址
        String apiKey = "b0bdf0cd-b041-4a06-939c-b8cc2880af99";
        String callback = "http://67.216.219.13:8080";
        final String onNotification = "KEEP";

        String callbackEncode = URLEncoder.encode(callback, "utf-8");

        urlStringBuffer.append(url).append("?key=").append(apiKey)
                .append("&address=").append(address).append("&callback=").
                append(callbackEncode).append("&onNotification=").append(onNotification);


        return urlStringBuffer.toString();
    }



}
