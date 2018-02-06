package com.pgb.spider.http.client.okhttp;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Clayburn
 * @date : 2018/1/16 19:29
 * @description
 */
public class CookieManager implements CookieJar {
    private Map<String, Cookie> cookies = new HashMap<String,Cookie>();

    private String cookie;

    public CookieManager() {
    }

    public CookieManager(String cookie) {
        this.cookie = cookie;
    }

    @Override
    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        if (list != null && !list.isEmpty()) {
            for (Cookie cookie : list) {
                cookies.put(cookie.name(), cookie);
            }
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
        if (cookies.isEmpty()) {
            Cookie.Builder builder1 = new Cookie.Builder().name("user_trace_token").value("20171130215959-c0f215a3-d5d6-11e7-b638-525400f775ce").domain("lagou.com");
            cookies.put("user_trace_token", builder1.build());
            Cookie.Builder builder2 = new Cookie.Builder().name("LGUID").value("20171130215959-c0f21bc9-d5d6-11e7-b638-525400f775ce").domain("lagou.com");
            cookies.put("LGUID", builder2.build());
            Cookie.Builder builder3 = new Cookie.Builder().name("_ga").value("GA1.2.1566320900.1512050401").domain("lagou.com");
            cookies.put("_ga", builder3.build());
            Cookie.Builder builder4 = new Cookie.Builder().name("_gid").value("GA1.2.526155492.1516888874").domain("lagou.com");
            cookies.put("_gid", builder4.build());
            Cookie.Builder builder5 = new Cookie.Builder().name("LGSID").value("20180125220115-356c85ee-01d8-11e8-9bec-525400f775ce").domain("lagou.com");
            cookies.put("LGSID", builder5.build());
            Cookie.Builder builder6 = new Cookie.Builder().name("Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6").value("1516712821,1516888874,1516890634,1516890834").domain("lagou.com");
            cookies.put("Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6", builder6.build());
            Cookie.Builder builder7 = new Cookie.Builder().name("X_HTTP_TOKEN").value("5d6ccaffe707a8d6d7d9ca91c9ea1ba2").domain("lagou.com");
            cookies.put("X_HTTP_TOKEN", builder7.build());
            Cookie.Builder builder8 = new Cookie.Builder().name("_putrc").value("6DF897287A973B80").domain("lagou.com");
            cookies.put("_putrc", builder8.build());
            Cookie.Builder builder9 = new Cookie.Builder().name("login").value("true").domain("lagou.com");
            cookies.put("login", builder9.build());
            Cookie.Builder builder10 = new Cookie.Builder().name("unick").value("%E5%BD%AD%E5%8A%9F%E6%B3%A2").domain("lagou.com");
            cookies.put("unick", builder10.build());
            Cookie.Builder builder11 = new Cookie.Builder().name("gate_login_token").value("4989770c290f880736332b545e6e5ba6ab6744e9d1481658").domain("lagou.com");
            cookies.put("gate_login_token", builder11.build());
            Cookie.Builder builder12 = new Cookie.Builder().name("index_location_city").value("%E5%8C%97%E4%BA%AC").domain("lagou.com");
            cookies.put("index_location_city", builder12.build());
            Cookie.Builder builder13 = new Cookie.Builder().name("_gat").value("1").domain("lagou.com");
            cookies.put("_gat", builder13.build());
            Cookie.Builder builder14 = new Cookie.Builder().name("LGRID").value("20180125231718-d51bcfb7-01e2-11e8-9bf1-525400f775ce").domain("lagou.com");
            cookies.put("LGRID", builder14.build());
            Cookie.Builder builder15 = new Cookie.Builder().name("SEARCH_ID").value("7587e152a3b14eec8bb0f29e774e4094").domain("lagou.com");
            cookies.put("SEARCH_ID", builder15.build());
            return new ArrayList<>(cookies.values());
        } else {

            return new ArrayList<>(cookies.values());
        }
    }

    public Map<String, Cookie> getCookies() {
        return cookies;
    }

    public void addCookies(Map<String, Cookie> cookies) {
        this.cookies.putAll(cookies);
    }
}
