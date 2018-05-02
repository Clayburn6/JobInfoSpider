package com.pgb.spider.wechat.controller;

import com.pgb.spider.entity.JobItem;
import com.pgb.spider.wechat.service.CollectService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Clayburn
 * @date : 2018/5/1 13:19
 * @description
 */
@RestController
public class CollectController {
    private static final Logger log = LoggerFactory.getLogger(CollectController.class);

    @Autowired
    private CollectService collectService;

    @RequestMapping(path = "/collect", method = RequestMethod.GET)
    public void collectJob(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies= request.getCookies();
        String openid = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("openid")) {
                    openid = cookie.getValue();
                }
            }
        }
        Integer jobId = Integer.parseInt(request.getParameter("jobId"));

        if (StringUtils.isBlank(openid) || jobId == null) {
            response.sendRedirect("/fail.html");
            return ;
        }
        if (!collectService.checkCollect(openid, jobId)) {
            collectService.collectByOpenidAndJobId(openid, jobId);
        }
        log.info("收藏成功！openid = {}, jobId = {}", openid, jobId);

//        response.sendRedirect("/detail.html?id=" + jobId);
    }
    @RequestMapping(path = "/uncollect", method = RequestMethod.GET)
    public void uncollectJob(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies= request.getCookies();
        String openid = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("openid")) {
                    openid = cookie.getValue();
                }
            }
        }
        Integer jobId = Integer.parseInt(request.getParameter("jobId"));

        if (StringUtils.isBlank(openid) || jobId == null) {
            response.sendRedirect("/fail.html");
            return ;
        }
        if (collectService.checkCollect(openid, jobId)) {
            collectService.unCollectByOpenidAndJobId(openid, jobId);
        }
        log.info("取消收藏成功！openid = {}, jobId = {}", openid, jobId);
//        response.sendRedirect("/detail.html?id=" + jobId);
    }
    @RequestMapping(path = "/checkCollect", method = RequestMethod.GET)
    public boolean checkCollectJob(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies= request.getCookies();
        String openid = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("openid")) {
                    openid = cookie.getValue();
                }
            }
        }
        Integer jobId = Integer.parseInt(request.getParameter("jobId"));

        if (StringUtils.isBlank(openid) || jobId == null) {
            response.sendRedirect("/fail.html");
            return false;
        }

        return collectService.checkCollect(openid, jobId);
    }

    @RequestMapping(path = "/getCollect", method = RequestMethod.GET)
    public List<JobItem> getCollect(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies= request.getCookies();
        String openid = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("openid")) {
                    openid = cookie.getValue();
                }
            }
        }

        return collectService.getCollect(openid);
    }
}
