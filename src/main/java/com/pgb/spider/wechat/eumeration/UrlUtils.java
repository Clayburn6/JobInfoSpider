package com.pgb.spider.wechat.eumeration;

public class UrlUtils {
    public static String getCodeUrl() {
        return "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Constant.appid + "&redirect_uri=" +
                Constant.redirect_uri + "&response_type=code&scope=snsapi_base&state=test#wechat_redirect";
    }

    public static String getOpenIdUrl(String code) {
        return "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + Constant.appid + "&secret=" +
                Constant.appsecret +"&code=" + code + "&grant_type=authorization_code";
    }
}
