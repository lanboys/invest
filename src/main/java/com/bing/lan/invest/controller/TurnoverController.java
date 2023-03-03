package com.bing.lan.invest.controller;

import com.bing.lan.invest.spider.Spider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping("/turnover")
@Slf4j
public class TurnoverController {

    @Autowired
    Spider spider;

    @RequestMapping(value = "/spider/start")
    public String turnoverSpiderStart() {
        spider.turnoverSpiderStart();
        return "ok";
    }
}
