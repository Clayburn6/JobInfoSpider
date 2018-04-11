package com.pgb.spider.web.controller;

import com.pgb.spider.entity.JobItem;
import com.pgb.spider.web.service.QueryService;
import com.pgb.spider.web.utils.Pagination;
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
    @Autowired
    private QueryService queryService;

    @GetMapping(path = "/query")
    public Pagination<JobItem> query(@RequestParam(name = "title", required = false) String title,
                                     @RequestParam(name = "money", required = false) Integer money,
                                     @RequestParam(name = "company", required = false) String company,
                                     @RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex,
                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
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
