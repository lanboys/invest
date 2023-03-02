package com.bing.lan.invest.controller;

import com.bing.lan.invest.domain.dto.AssertBean;
import com.bing.lan.invest.domain.dto.MitmproxyDto;
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
 * @since 2023-02-07
 */
@RestController
@RequestMapping("/mitmproxy")
@Slf4j
public class MitmproxyController {

    @Autowired
    MitmproxyService mitmproxyService;

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public String file(@RequestParam MultipartFile file, @RequestParam String accountCode) {
        try {
            byte[] bytes = file.getBytes();
            AssertBean assertBean = JSONUtil.toBean(new String(bytes), AssertBean.class);
            log.info("资产数据：{}", assertBean);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "ok";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestBody MitmproxyDto mitmproxyDto) {
        log.info("mitmproxy url：{}", mitmproxyDto.getUrl());
        if (!"qieman.com".equals(mitmproxyDto.getHost())) {
            log.info("skip...");
            return "skip";
        }
        mitmproxyService.parseMitmproxyData(mitmproxyDto);
        return "ok";
    }
}
