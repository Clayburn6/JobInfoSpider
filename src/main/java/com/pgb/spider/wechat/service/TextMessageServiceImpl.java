package com.pgb.spider.wechat.service;


import com.pgb.spider.wechat.dao.TextRequestDao;
import com.pgb.spider.wechat.dao.UserInfoDao;
import com.pgb.spider.wechat.entity.UserInfo;
import com.pgb.spider.wechat.eumeration.Constant;
import com.pgb.spider.wechat.eumeration.MsgType;
import com.pgb.spider.wechat.xom.JAXBUtils;
import com.pgb.spider.wechat.xom.request.TextRequest;
import com.pgb.spider.wechat.xom.response.TextResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TextMessageServiceImpl implements TextMessageService {
    private static final Logger logger = LoggerFactory.getLogger(TextMessageServiceImpl.class);

    @Autowired
    private TextRequestDao textRequestDao;

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    @Transactional
    public String dealWithText(String xml) throws Exception {
        logger.info("处理文本消息");
        TextRequest request = JAXBUtils.unmarshal(xml, TextRequest.class);

        logger.info("持久化文本消息, 内容：\n" + request.toString());
        textRequestDao.save(request);

        TextResponse response = new TextResponse();
        response.setToUserName(request.getFromUserName());
        response.setFromUserName(request.getToUserName());
        response.setCreateTime(System.currentTimeMillis() + "");

        response.setMsgType(MsgType.text.getCode());
        response.setMsgId(System.currentTimeMillis() + "");

        String content = request.getContent();
        if (!content.matches("【.*】\\+【.*】\\+【.*】")) {
            response.setContent(Constant.SET_USERINFO);
        } else {
            // 解析content中的内容
            String[] strArray = content.split("\\+");
            for (int i = 0; i < strArray.length; i++) {
                strArray[i] = strArray[i].substring(1, strArray[i].length() - 1);
            }
            String title = strArray[0];
            String salary = strArray[1];
            String company = strArray[2];
            logger.info("用户的信息为： title = " + title + ", salary = " + salary + ", company = " + company);

            // 看用户表中是否存在
            UserInfo userInfo = userInfoDao.findByWechatCodeAndOpenidAndDeleteFlagFalse(request.getToUserName(), request.getFromUserName());
            if (userInfo == null) {
                userInfo = new UserInfo();
                userInfo.setWechatCode(request.getToUserName());
                userInfo.setOpenid(request.getFromUserName());
                userInfo.setTitle(title);
                userInfo.setSalary(Integer.parseInt(salary));
                userInfo.setCompany(company);
                userInfoDao.save(userInfo);
            } else {
                userInfo.setTitle(title);
                userInfo.setSalary(Integer.parseInt(salary));
                userInfo.setCompany(company);
            }
            response.setContent("您的信息我们已经了解，请尽情使用吧！");
        }


        String result = JAXBUtils.marshal(response);

        logger.info("响应消息, msgId = " + response.getMsgId());


        return result;
    }
}
