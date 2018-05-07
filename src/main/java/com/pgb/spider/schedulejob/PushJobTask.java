package com.pgb.spider.schedulejob;

import com.pgb.spider.entity.JobItem;
import com.pgb.spider.web.dao.QueryDao;
import com.pgb.spider.wechat.dao.UserInfoDao;
import com.pgb.spider.wechat.entity.Text;
import com.pgb.spider.wechat.entity.UserInfo;
import com.pgb.spider.wechat.eumeration.UrlUtils;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import okhttp3.*;
import okio.BufferedSink;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Clayburn
 * @date : 2018/5/2 16:08
 * @description
 */
@Component
public class PushJobTask {

    private static final Logger log = LoggerFactory.getLogger(PushJobTask.class);

    public static final MediaType JSON=MediaType.parse("application/json; charset=utf-8");

    @Autowired
    private QueryDao queryDao;

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Scheduled(fixedRate = 86400)
    public void pushJobTask() throws IOException {
        long total = queryDao.count();
        Random random = new Random(System.currentTimeMillis());
        List<Integer> ids = new ArrayList<>();
        ids.add(random.nextInt((int)total) + 1);
        ids.add(random.nextInt((int)total) + 1);
        ids.add(random.nextInt((int)total) + 1);

        List<JobItem> list = queryDao.getByIdIn(ids);

        if (list == null || list.isEmpty()) {
            log.info("未查询到职位信息！");
        }

        StringBuilder sb = new StringBuilder("为您提供如下岗位的信息：\n");

        for (JobItem jobItem : list) {
            sb.append("\n【职位名称】\n" + jobItem.getTitle());
            sb.append("\n【薪资待遇】\n" + jobItem.getMoney());
            sb.append("\n【招聘单位】\n" + jobItem.getCompany());
            sb.append("\n【岗位要求】\n点击<a href=\"http://penggb.top/detail.html?id=" + jobItem.getId() + "\">这里</a>访问详情");
            sb.append("\n");
        }

        log.info("获取的职位信息：{}", sb.toString());

        List<UserInfo> userInfos = userInfoDao.findAll();
        if (userInfos == null || userInfos.isEmpty()) {
            return;
        }

        List<String> openids = new ArrayList<String>();
        for (UserInfo info : userInfos) {
            openids.add(info.getOpenid());
        }

        String accessToken = redisTemplate.opsForValue().get("access_token");
        okhttp3.OkHttpClient.Builder clientBuilder = new okhttp3.OkHttpClient.Builder();
        if (StringUtils.isBlank(accessToken)) {

            Request okRequest = new Request.Builder().url(UrlUtils.getAccessToken()).get().build();
            Response okResponse = clientBuilder.build().newCall(okRequest).execute();

            okhttp3.ResponseBody responseBody = okResponse.body();
            String jsonString = responseBody.string();

            JSONObject jsonObject = JSONObject.fromObject(jsonString);
            accessToken = jsonObject.get("access_token").toString();
            redisTemplate.opsForValue().set("access_token", accessToken, 5400, TimeUnit.SECONDS);
        }

        log.info("accessToken = " + accessToken);

        // 发送群发请求
        JSONObject json = new JSONObject();
        json.put("msgtype", "text");
        json.put("touser", openids);
        Text text = new Text();
        text.setContent(sb.toString());
        json.put("text", text);
        RequestBody body = RequestBody.create(JSON, json.toString());
        Request qunfaRequest = new Request.Builder().url(UrlUtils.getQunfa(accessToken)).post(body).build();
        Response qunResponse = clientBuilder.build().newCall(qunfaRequest).execute();

        okhttp3.ResponseBody qunfaBody = qunResponse.body();
        String result = qunfaBody.string();
        JSONObject jsonResult = JSONObject.fromObject(result);

        log.info(jsonResult.get("errmsg").toString());
    }
}
