package com.pgb.spider.wechat.controller;

import org.hibernate.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping(path = "/validate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TokenCheckController {
    private static final Logger logger = LoggerFactory.getLogger(TokenCheckController.class);

    /**
     * 微信服务器会以get方式请求服务器，并传入signature、timestamp、nonce、echostr四个参数
     * 验证接口配置信息
     * 加密/校验流程如下：
     * 1. 将token、timestamp、nonce三个参数进行字典序排序
     * 2. 将三个参数字符串拼接成一个字符串进行sha1加密
     * 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     *
     * @param signature 微信加密签名
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @param echostr 随机字符串，用于返回给微信服务器作比较
     */
    public String checkToken(@RequestParam(name = "signature", defaultValue = "") String signature,
                           @RequestParam(name = "timestamp", defaultValue = "") String timestamp,
                           @RequestParam(name = "nonce", defaultValue = "") String nonce,
                           @RequestParam(name = "echostr", defaultValue = "") String echostr) {
        String token = "F0932398023";
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
