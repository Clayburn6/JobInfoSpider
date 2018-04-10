package com.pgb.spider.wechat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class UserInfoController {
    private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @RequestMapping(value = "/wechat/setUserInfo", method = RequestMethod.POST)
    public void setUserInfo(String title, Integer salary, String company ) {
        logger.info("{} + {} + {}", title, salary, company);
    }
}
