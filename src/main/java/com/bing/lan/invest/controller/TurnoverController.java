package com.bing.lan.invest.controller;

import com.bing.lan.invest.domain.dto.AssertBean;
import com.bing.lan.invest.service.AccountService;
import com.bing.lan.invest.utils.Spider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 投资基金详情 前端控制器
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

    @RequestMapping(value = "/spider/start", method = RequestMethod.POST)
    public String spiderStart() {
        spider.spiderStart();
        return "ok";
    }
}
