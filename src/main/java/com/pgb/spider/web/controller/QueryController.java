package com.pgb.spider.web.controller;

import com.pgb.spider.entity.JobItem;
import com.pgb.spider.web.service.QueryService;
import com.pgb.spider.web.utils.Pagination;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Clayburn
 * @date : 2018/2/8 12:05
 * @description
 */
@RestController
@RequestMapping(path = "/query", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class QueryController {
    private static final Logger logger = LoggerFactory.getLogger(QueryController.class);

    @Autowired
    private QueryService queryService;

    @GetMapping(path = "/query")
    public Pagination<JobItem> query(@RequestParam(name = "searchValue", required = false) String searchValue,
                                     @RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        logger.info(searchValue);
        String title = null;
        Integer money = null;
        String company = null;

        if (StringUtils.isNotBlank(searchValue)) {
            String[] strArray = searchValue.split("\\s");
            if (strArray.length == 1) {
                title = strArray[0];
            } else if (strArray.length == 2) {
                title = strArray[0];
                try {
                    money = Integer.parseInt(strArray[1]);
                } catch (Exception e) {
                    company = strArray[1];
                }
            }
            if (strArray.length == 3) {
                title = strArray[0];
                try {
                    money = Integer.parseInt(strArray[1]);
                } catch (Exception e) {
                    money = null;
                }
                company = strArray[2];
            }
        }
        return queryService.query(title, money, company, pageIndex, pageSize);
    }

    @GetMapping(path = "/detail")
    public JobItem queryDetail(Integer id) {
        return queryService.queryDetail(id);
    }

    @GetMapping(path = "/hello")
    @ResponseBody
    public String test() {
        return "hello";
    }
}
