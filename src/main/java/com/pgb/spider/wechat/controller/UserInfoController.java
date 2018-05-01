package com.pgb.spider.wechat.controller;

import com.pgb.spider.http.client.okhttp.OkHttpClient;
import com.pgb.spider.web.dao.QueryDao;
import com.pgb.spider.wechat.dao.UserInfoDao;
import com.pgb.spider.wechat.entity.UserInfo;
import com.pgb.spider.wechat.eumeration.Constant;
import com.pgb.spider.wechat.eumeration.UrlUtils;
import com.pgb.spider.wechat.xom.response.OpenIdResponse;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用来出处理设置个人信息
 */
@Controller
@ResponseBody
public class UserInfoController {
    private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private UserInfoDao userInfoDao;


    /*
        判断请求的cookie中是否携带openid。如果携带直接跳转到userinfo.html,如果不携带，重定向请求，跳转到/wechat/setopenid中去，
        然后获取到了openid设置cookie，再重定向到userinfo.html
     */

    /**
     * 检查cookie中是否携带openid
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/wechat/checkOpenid", method = RequestMethod.GET)
    public void setUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies= request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("openid")) {
                    String openid = cookie.getValue();
                    if (StringUtils.isNotBlank(openid)) {
                        logger.info("在cookie中获取到openid = {}", openid);
                        response.sendRedirect("/userdetail.html");
                        return;
                    }
                }
            }
        }
        logger.info("在cookie中未找到openid，重定向请求微信服务器");
        // 没找到openid的cookie，重定向去请求微信服务器
        response.sendRedirect(UrlUtils.getCodeUrl());
    }

    /**
     * 请求微信服务器获取openid
     * @param request
     * @param response
     * @param code
     * @param state
     * @throws IOException
     */
    @RequestMapping(value ="/wechat/getOpenid", method = RequestMethod.GET)
    public void getWechatOpenid(HttpServletRequest request, HttpServletResponse response, String code, String state) throws IOException {
        logger.info("code = {}, state = {}", code, state);

        // 使用okhttp请求来获取access_token
        okhttp3.OkHttpClient.Builder clientBuilder = new okhttp3.OkHttpClient.Builder();
        Request okRequest = new Request.Builder().url(UrlUtils.getOpenIdUrl(code)).get().build();
        Response okResponse = clientBuilder.build().newCall(okRequest).execute();

        okhttp3.ResponseBody responseBody = okResponse.body();
        String jsonString = responseBody.string();

        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        OpenIdResponse openIdResponse = (OpenIdResponse) JSONObject.toBean(jsonObject, OpenIdResponse.class);

        logger.info("微信服务器响应内容是：\n{}", openIdResponse.toString());

        String openid = openIdResponse.getOpenid();
        Cookie cookie = new Cookie("openid", openid);
        cookie.setPath("/");
        cookie.setMaxAge(30*24*60*60); // 一个月
        response.addCookie(cookie);
        response.sendRedirect("/userdetail.html");
    }

    @Transactional
    @RequestMapping(value = "/wechat/setUserInfo", method = RequestMethod.POST)
    public void setUserInfo(HttpServletRequest request, HttpServletResponse response, String title, Integer salary, String company) throws IOException {
        // 这里应该存在cookie
        Cookie[] cookies= request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("openid")) {
                    String openid = cookie.getValue();
                    if (StringUtils.isNotBlank(openid)) {
                        logger.info("在cookie中获取到openid = {}", openid);
                        UserInfo userInfo = userInfoDao.getByOpenidAndDeleteFlagFalse(openid);
                        userInfo.setTitle(title);
                        userInfo.setSalary(salary);
                        userInfo.setCompany(company);
                        response.sendRedirect("/userdetail.html");
                        return;
                    }
                }
            }
        }

        setUserInfo(request, response);
    }

    @RequestMapping(value = "/wechat/getUserInfo", method = RequestMethod.GET)
    @Transactional
    public UserInfo getUserInfo(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies= request.getCookies();
        String openid = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("openid")) {
                    openid = cookie.getValue();
                }
            }
        }


        return  userInfoDao.getByOpenidAndDeleteFlagFalse(openid);
    }
}
