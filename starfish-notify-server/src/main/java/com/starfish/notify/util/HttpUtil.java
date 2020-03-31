package com.starfish.notify.util;



import com.google.gson.Gson;

import org.apache.commons.collections.MapUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.client.fluent.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author yizijun
 * @version 1.0.0
 * @since 2019-04-24
 */
public class HttpUtil {

    private static final int DEFAULT_TIMEOUT = 10000;

    //线程安全
    private static Gson gsonIntance = new Gson();

    private static String[] userAgents = {
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/603.3.8 (KHTML, like Gecko) Version/10.1.2 Safari/603.3.8",
    };

    /**
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static HttpResponse getWithReturnResponse(String url) throws IOException {
        return getRequest(url).execute().returnResponse();
    }

    public static String get(String url) throws IOException {
        return getRequest(url).execute().returnContent().asString();
    }

    public static String get(String url,Map<String,String> headers)  throws IOException  {
        Request request = getRequest(url,headers);
        return request.execute().returnContent().asString();
    }

    public static Response getResponse(String url) throws IOException {
        return getRequest(url).execute();
    }

    public static byte[] getAsBytes(String url) throws IOException {
        return getRequest(url).execute().returnContent().asBytes();
    }

    /**
     * doPostWithReturnResponse
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static HttpResponse doPostWithReturnResponse(String url, Object params) throws IOException {
        return postRequest(url).bodyString(gsonIntance.toJson(params), ContentType.APPLICATION_JSON).execute().returnResponse();
    }


    public static Object doPost(String url, Object params) throws IOException {
        return postRequest(url).bodyString(gsonIntance.toJson(params), ContentType.APPLICATION_JSON).execute().returnContent().asString();
    }

    public static String doPost(String url, Object params, Map<String,String> headers) throws IOException {
        Request request = postRequest(url);
        if (MapUtils.isNotEmpty(headers)) {
            for (Map.Entry<String,String> entry:headers.entrySet()) {
                request.setHeader(entry.getKey(), entry.getValue());
            }
        }
        return request.bodyString(gsonIntance.toJson(params), ContentType.APPLICATION_JSON).execute().returnContent().asString();
    }

    public static <T> T doPost(String url, Object params, Class<T> cls) throws IOException {
        String json = postRequest(url).bodyString(gsonIntance.toJson(params), ContentType.APPLICATION_JSON).execute().returnContent().asString();
        T ret = gsonIntance.fromJson(json, cls);

        return ret;
    }

    public static <T> T doPost(String url, Object params, Class<T> cls, int timeoutms) throws IOException {
        String json = postRequest(url, timeoutms).bodyString(gsonIntance.toJson(params), ContentType.APPLICATION_JSON).execute().returnContent().asString();
        T ret = gsonIntance.fromJson(json, cls);
        return ret;
    }


    public static <T> T doPost(String url, Object params, Map<String,String> headers, Class<T> cls) throws IOException {
        Gson gson = new Gson();

        Request request = postRequest(url);
        if (MapUtils.isNotEmpty(headers)) {
            for (Map.Entry<String,String> entry:headers.entrySet()) {
                request.setHeader(entry.getKey(), entry.getValue());
            }
        }
        String json = request.bodyString(gson.toJson(params), ContentType.APPLICATION_JSON).execute().returnContent().asString();
        T ret = gson.fromJson(json, cls);

        return ret;
    }


    public static Request postRequest(String url) {
        int index = (int) (Math.random() * userAgents.length);

        String userAgent = userAgents[index];

        return Request.Post(url).connectTimeout(DEFAULT_TIMEOUT).socketTimeout(DEFAULT_TIMEOUT).userAgent(userAgent);
    }

    private static Request postRequest(String url, int timeoutms) {
        int index = (int) (Math.random() * userAgents.length);

        String userAgent = userAgents[index];

        return Request.Post(url).connectTimeout(timeoutms).socketTimeout(timeoutms).userAgent(userAgent);
    }

    private static Request getRequest(String url) {
        int index = (int)(Math.random() * userAgents.length);

        String userAgent = userAgents[index];

        return Request.Get(url).connectTimeout(DEFAULT_TIMEOUT).socketTimeout(DEFAULT_TIMEOUT).userAgent(userAgent);
    }

    private static Request getRequest(String url, Map<String,String> headers) {
        int index = (int)(Math.random() * userAgents.length);
        String userAgent = userAgents[index];
        Request req = Request.Get(url);
        for(Map.Entry<String, String> entry : headers.entrySet()) {
            req.addHeader(entry.getKey(), entry.getValue());
        }
        return req.connectTimeout(DEFAULT_TIMEOUT).socketTimeout(DEFAULT_TIMEOUT).userAgent(userAgent);
    }


    public static Map<String,String>  getMasterWebPasswortHeaders(String xEntry,String xEntrySecret, String xVersion) {
        Map<String,String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");
        headerMap.put("X-Entry", xEntry);
        headerMap.put("X-Entry-Secret", xEntrySecret);
        headerMap.put("X-Version", xVersion);
        headerMap.put("X-Timestamp", System.currentTimeMillis()+"");
        headerMap.put("X-Client-Ip", "127.0.0.1");
        return headerMap;
    }

    public static void main(String[] args) throws Exception {

    }

}

