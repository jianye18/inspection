package com.zhuhong.inspection.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * httpclient 工具类
 * @author 叶剑
 */
@Slf4j
public class HttpClientUtil {

    /**
     * 超时参数设置:
     * -- setConnectTimeout：          设置连接超时时间，单位毫秒。
     * -- setConnectionRequestTimeout: 设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
     * -- setSocketTimeout：           请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
     */
    private static RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(60 * 1000)
            .setConnectionRequestTimeout(1000)
            .setSocketTimeout(60 * 1000)
            .build();

    public static HttpEntity get(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 使用的请求方法
        HttpGet httpget = new HttpGet(url);
        httpget.setConfig(requestConfig);
        // 请求头配置
        httpget.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        httpget.setHeader("Accept-Encoding", "gzip, deflate");
        httpget.setHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        httpget.setHeader("Cache-Control", "max-age=0");
        // //这项内容很重要
        httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36");
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpget);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 获取网页内容流
        assert response != null;
        System.out.println(JSON.toJSONString(response.getEntity()));
        return response.getEntity();
    }

    public static String getRawHtml(String url) throws URISyntaxException, IOException {
        URI uri = new URIBuilder(url).build();
        HttpClientContext httpClientContext = HttpClientContext.create();
        //请求头添加
        List<Header> headerList = Lists.newArrayList();
        headerList.add(new BasicHeader(HttpHeaders.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9," +
                "image/webp,image/apng,*/*;q=0.8"));
        headerList.add(new BasicHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36"));
        headerList.add(new BasicHeader(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate"));
        headerList.add(new BasicHeader(HttpHeaders.CACHE_CONTROL, "max-age=0"));
        headerList.add(new BasicHeader(HttpHeaders.CONNECTION, "keep-alive"));
        headerList.add(new BasicHeader(HttpHeaders.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4,ja;q=0.2," +
                "de;q=0.2"));
        //httpClient初始化
        HttpClient httpClient = HttpClients.custom().setDefaultHeaders(headerList).build();
        //获取响应内容
        HttpUriRequest httpUriRequest = RequestBuilder.get().setUri(uri).build();
        httpClient.execute(httpUriRequest, httpClientContext);
        HttpResponse httpResponse = httpClient.execute(httpUriRequest, httpClientContext);
        //获取返回结果中的实体
        HttpEntity entity = httpResponse.getEntity();
        String html = "<html>" + EntityUtils.toString(entity) + "</html>";
        System.out.println(html);
        return html;
    }

    public static void main(String[] args) {
        try {
            String html = getRawHtml("http://www.yezhuhongblog.com/?cate=2");
            List<String> childrenUrlList = new ArrayList<>();
            //采用Jsoup解析
            Document doc = Jsoup.parse(html);
            //采取html标签中的内容
            Elements elements = doc.select("article[class=archive-list]");
            for (Element element : elements) {
                String url = element.select("figure[class=thumbnail]").select("a").attr("href");
                childrenUrlList.add(url);
            }
            for (String url : childrenUrlList) {
                String childrenHtml = getRawHtml(url);
                System.out.println(url);
            }
            System.out.println(childrenUrlList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
