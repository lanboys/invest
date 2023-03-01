package com.bing.lan.invest.controller;

import com.bing.lan.invest.domain.dto.AssertBean;
import com.bing.lan.invest.domain.dto.MitmproxyDto;
import com.bing.lan.invest.utils.UrlUtil;

import org.springframework.util.ObjectUtils;
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

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public String file(@RequestParam MultipartFile file, @RequestParam String accountCode) {
        try {
            byte[] bytes = file.getBytes();
            AssertBean assertBean = JSONUtil.toBean(new String(bytes), AssertBean.class);
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
        longWin(mitmproxyDto);
        return "ok";
    }

    public void longWin(MitmproxyDto mitmproxyDto) {
        UrlUtil.UrlEntity urlEntity = UrlUtil.parse(mitmproxyDto.getUrl());
        if ("https://qieman.com/pmdj/v2/long-win/ca/assets-summary".equals(urlEntity.getUrl())
                && !ObjectUtils.isEmpty(mitmproxyDto.getBody())) {
            log.info("资产数据：{}", mitmproxyDto);
            log.info("解析前。。。");
            AssertBean assertBean = JSONUtil.toBean(mitmproxyDto.getBody(), AssertBean.class);
            log.info("解析后。。。");
        }
    }
}
