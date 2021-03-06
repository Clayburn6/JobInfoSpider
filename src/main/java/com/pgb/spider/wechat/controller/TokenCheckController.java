package com.pgb.spider.wechat.controller;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Clayburn
 * @date : 2018/3/25 13:32
 * @description 验证token的正确性
 */

@RestController
public class TokenCheckController {
    private static final Logger logger = LoggerFactory.getLogger(TokenCheckController.class);
    private static final String token = "Penggongbo023";

    /**
     * 响应微信服务器发来的请求
     */
    @RequestMapping(path="/wechat", method = RequestMethod.POST)
    public void processWeChatRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.getWriter().print("已收到消息");
    }

    /**
     * 微信服务器会以get方式请求服务器，并传入signature、timestamp、nonce、echostr四个参数
     * 验证接口配置信息
     * 加密/校验流程如下：
     * 1. 将token、timestamp、nonce三个参数进行字典序排序
     * 2. 将三个参数字符串拼接成一个字符串进行sha1加密
     * 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     *
     */
    @RequestMapping(path = "/wechat", method = RequestMethod.GET)
    public void checkToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String signature = request.getParameter("signature");
        signature = StringUtils.isBlank(signature) ? "" : signature;
        String timestamp = request.getParameter("timestamp");
        timestamp = StringUtils.isBlank(timestamp) ? "" : timestamp;
        String nonce = request.getParameter("nonce");
        nonce = StringUtils.isBlank(nonce) ? "" : nonce;
        String echostr = request.getParameter("echostr");
        echostr = StringUtils.isBlank(echostr) ? "" : echostr;

        response.getWriter().write(echostr);
        /*
        List<String> list = new ArrayList<>();
        list.add(timestamp);
        list.add(token);
        list.add(nonce);

        // 1.字典排序
        Collections.sort(list);

        // 2.sha1加密
        MessageDigest md = null;
        String tmpStr = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            StringBuilder sb = new StringBuilder();
            sb.append(list.get(0));
            sb.append(list.get(1));
            sb.append(list.get(2));
            byte[] digest = md.digest(sb.toString().getBytes());
            //将字节数组转成字符串
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getStackTrace().toString());
        }
        // 开发者获得加密后的字符串可与signature对比
        if (tmpStr != null && tmpStr.equals(signature.toUpperCase())) {
            return echostr;
        } else {
            return null;
        }
        */

    }
    //将加密后的字节数组变成字符串
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    private static String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }

}
