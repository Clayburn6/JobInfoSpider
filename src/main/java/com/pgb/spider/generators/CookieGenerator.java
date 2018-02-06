package com.pgb.spider.generators;

import com.pgb.spider.executer.Task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Clayburn
 * @date : 2018/1/16 19:04
 * @description
 */
public class CookieGenerator implements StringGenerator {
    private static Map<String, String> cookieMap = new HashMap<>();

    static {
        cookieMap.put("user_trace_token", getTime() + "-c0f215a3-d5d6-11e7-b638-525400f775ce");
        cookieMap.put("LGUID", getTime() + "-c0f21bc9-d5d6-11e7-b638-525400f775ce");
        cookieMap.put("_ga", "GA1.2.1566320900.1512050401");
        cookieMap.put("_gid", "GA1.2.526155492.1516888874");
        cookieMap.put("LGSID", "20180125220115-356c85ee-01d8-11e8-9bec-525400f775ce");
        cookieMap.put("Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6", "1516712821,1516888874,1516890634,1516890834");
        cookieMap.put("X_HTTP_TOKEN", "5d6ccaffe707a8d6d7d9ca91c9ea1ba2");
        cookieMap.put("_putrc", "6DF897287A973B80");
        cookieMap.put("login", "true");
        cookieMap.put("unick", "%E5%BD%AD%E5%8A%9F%E6%B3%A2");
        cookieMap.put("gate_login_token", "4989770c290f880736332b545e6e5ba6ab6744e9d1481658");
        cookieMap.put("index_location_city", "%E5%8C%97%E4%BA%AC");
        cookieMap.put("_gat", "1");
        cookieMap.put("Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6", "1516893437");
        cookieMap.put("LGRID", "20180125231718-d51bcfb7-01e2-11e8-9bf1-525400f775ce");
        cookieMap.put("", "");
        cookieMap.put("", "");
        cookieMap.put("", "");
    }

    @Override
    public String get(Task task) {
        return null;
    }

    @Override
    public String Set(Task task) {
        return null;
    }

    private static String getTime() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }
}
