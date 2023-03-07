package com.bing.lan.invest.controller;

import com.bing.lan.invest.domain.spider.qieman.AccountAssertBean;
import com.bing.lan.invest.domain.dto.MitmproxyDto;
import com.bing.lan.invest.service.AccountService;
import com.bing.lan.invest.service.MitmproxyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author oopcoder
 * @since 2023-03-01
 */
@RestController
@RequestMapping("/mitmproxy")
@Slf4j
public class MitmproxyController {

    @Autowired
    MitmproxyService mitmproxyService;

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public String file(@RequestParam MultipartFile file, @RequestParam String accountCode) {
        try {
            byte[] bytes = file.getBytes();
            AccountAssertBean assertBean = JSONUtil.toBean(new String(bytes), AccountAssertBean.class);
            log.info("资产数据：{}", assertBean);
            accountService.updateAssert(accountCode, assertBean);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "ok";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestBody MitmproxyDto mitmproxyDto) {
        log.info("mitmproxy url：{}", mitmproxyDto.getUrl());
        mitmproxyService.parseMitmproxyData(mitmproxyDto);
        return "ok";
    }
}
