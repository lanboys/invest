package com.bing.lan.invest.controller;

import com.bing.lan.invest.spider.QiemanSpider;
import com.bing.lan.invest.spider.SsjSpider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 账单流水 前端控制器
 * </p>
 *
 * @author oopcoder
 * @since 2023-03-03
 */
@RestController
@RequestMapping("/spider")
@Slf4j
public class SpiderController {

    @Autowired
    QiemanSpider qiemanSpider;
    @Autowired
    SsjSpider ssjSpider;

    @RequestMapping(value = "/qieman/turnover/start")
    public String turnoverStart() {
        qiemanSpider.turnoverStart();
        return "ok";
    }

    @RequestMapping(value = "/ssj/account/start")
    public String accountStart() {
        ssjSpider.accountStart();
        return "ok";
    }
}
