package com.pgb.spider.wechat.service;

import com.pgb.spider.entity.JobItem;
import com.pgb.spider.web.dao.ComplexQueryDao;
import com.pgb.spider.wechat.dao.SubscribeDao;
import com.pgb.spider.wechat.dao.UserInfoDao;
import com.pgb.spider.wechat.dom.DomUtils;
import com.pgb.spider.wechat.entity.UserInfo;
import com.pgb.spider.wechat.eumeration.Constant;
import com.pgb.spider.wechat.eumeration.EventKey;
import com.pgb.spider.wechat.eumeration.EventType;
import com.pgb.spider.wechat.eumeration.MsgType;
import com.pgb.spider.wechat.xom.JAXBUtils;
import com.pgb.spider.wechat.xom.request.MenuClick;
import com.pgb.spider.wechat.xom.request.Subscribe;
import com.pgb.spider.wechat.xom.response.TextResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@Service
public class EventMessageServiceImpl implements EventMessageService {
    private final static Logger logger = LoggerFactory.getLogger(EventMessageServiceImpl.class);

    @Autowired
    private SubscribeDao subscribeDao;

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private ComplexQueryDao complexQueryDao;


    @Override
    @Transactional
    public String dealWithEventMessage(String xml) throws Exception {
        // 解析消息，分一下是那种类型的事件消息
        String code = DomUtils.selectXmlByTagName(xml, "Event");
        EventType event = EventType.fromCode(code);
        switch (event) {
            case subscribe:
                return dealWithSubScribe(xml);
            case unsubscribe: // 取消订阅，删除该用户
                return dealWithUnSubcribe(xml);
            case CLICK:
                return dealWithClick(xml);
            default:
                return "success";
        }
    }

    /**
     * 订阅事件
     * @param xml
     * @return
     */

    private String dealWithSubScribe(String xml) throws JAXBException, IOException {
        logger.info("处理订阅事件");
        // 用jaxb将String转成object
        Subscribe subscribe = JAXBUtils.unmarshal(xml, Subscribe.class);
        logger.info("持久化订阅消息, 内容：\n" + subscribe.toString());
        subscribeDao.save(subscribe);
        // 生成xml消息
        TextResponse response = new TextResponse();
        response.setToUserName(subscribe.getFromUserName());
        response.setFromUserName(subscribe.getToUserName());
        response.setCreateTime(System.currentTimeMillis() + "");
        response.setContent(Constant.WELCOME_INFO);
        response.setMsgType(MsgType.text.getCode());
        response.setMsgId(System.currentTimeMillis() + "");

        String result = JAXBUtils.marshal(response);

        logger.info("响应消息, msgId = " + response.getMsgId());


        return result;
    }

    /**
     * 取消订阅
     * @param xml
     * @return
     * @throws JAXBException
     */
    private String dealWithUnSubcribe(String xml) throws JAXBException {
        Subscribe subscribe = JAXBUtils.unmarshal(xml, Subscribe.class);
        if (subscribe == null) {
            throw new RuntimeException("消息解析失败！");
        }
        logger.info("取消订阅事件， openid = " + subscribe.getFromUserName() + ", 微信号 = " + subscribe.getToUserName());

        subscribeDao.save(subscribe);

        UserInfo userInfo = userInfoDao.findByWechatCodeAndOpenidAndDeleteFlagFalse(subscribe.getToUserName(), subscribe.getFromUserName());
        userInfo.setDeleteFlag(true);
        logger.info("用户已逻辑删除删除， openid = " + subscribe.getFromUserName() + ", 微信号 = " + subscribe.getToUserName());

        return "success";
    }

    /**
     * 自定义菜单点击事件
     * @param xml
     * @return
     * @throws JAXBException
     */
    private String dealWithClick(String xml) throws JAXBException, IOException {
        MenuClick menuClick = JAXBUtils.unmarshal(xml, MenuClick.class);

        EventKey eventKey = EventKey.fromCode(menuClick.getEventKey());

        UserInfo userInfo = userInfoDao.findByWechatCodeAndOpenidAndDeleteFlagFalse(menuClick.getToUserName(), menuClick.getFromUserName());

        // 生成xml消息
        TextResponse response = new TextResponse();
        response.setToUserName(menuClick.getFromUserName());
        response.setFromUserName(menuClick.getToUserName());
        response.setCreateTime(System.currentTimeMillis() + "");
        response.setMsgType(MsgType.text.getCode());
        response.setMsgId(System.currentTimeMillis() + "");

        if (userInfo == null) {
            logger.info("用户未设置个人信息，提示用户先设置个人信息");
            response.setContent(Constant.WARNING_SET_USERINFO);
        } else {
            switch (eventKey) {
                case VPGB_Find_Work:
                    response.setContent(findWork(userInfo.getTitle(), userInfo.getSalary(), userInfo.getCompany()));
                    break;
                case VPGB_Refresh_Work:
                    response.setContent(freshWork(userInfo.getTitle(), userInfo.getSalary(), userInfo.getCompany()));
                    break;
                default:
                    return "success";
            }
        }

        return JAXBUtils.marshal(response);
    }

    /**
     * 处理自定义菜单里面“找工作按钮”，查询符合条件的数据，并编辑成xml返回给微信服务器
     * @param title
     * @param money
     * @param company
     * @return
     */
    private String findWork(String title, Integer money, String company) {
        Integer count = complexQueryDao.countJobItem(title, money, company);
        if (count == null || count == 0) {
            return "暂时没有符合您条件的信息";
        }
        // 求出总页数
        Integer totalPages = count % 5 == 0 ? count / 10 : count / 10 + 1;
        Random random = new Random(System.currentTimeMillis());

        // 页码随机
        Integer pageIndex = random.nextInt() % totalPages + 1;

        List<JobItem> resultList = complexQueryDao.findJobItemList(title, money, company, pageIndex, 5);
        StringBuilder sb = new StringBuilder("为您提供如下岗位的信息：");

        for (JobItem jobItem : resultList) {
            sb.append("【职位名称】：\n" + jobItem.getTitle());
            sb.append("【薪资待遇】：\n" + jobItem.getMoney());
            sb.append("【招聘单位】：\n" + jobItem.getCompany());
            sb.append("【岗位要求】：\n详细请访问" + "www.penggb.top/query/detail?id=" + jobItem.getId());
            sb.append("\n");
        }


        return sb.toString();
    }

    /**
     * 处理自定义菜单里面“换一批“按钮”，查询符合条件的数据，并编辑成xml返回给微信服务器
     * @param title
     * @param money
     * @param company
     * @return
     */
    private String freshWork(String title, Integer money, String company) {

        return findWork(title, money, company);
    }
}
