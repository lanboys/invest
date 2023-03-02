package com.bing.lan.invest.service.impl;

import com.bing.lan.invest.domain.dto.AssertBean;
import com.bing.lan.invest.domain.dto.MitmproxyDto;
import com.bing.lan.invest.service.MitmproxyService;
import com.bing.lan.invest.utils.UrlUtil;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by lanbing at 2023/3/1 18:28
 */

@Service
@Slf4j
@Transactional
public class MitmproxyServiceImpl implements MitmproxyService {

    @Override
    @Async
    public void parseMitmproxyData(MitmproxyDto mitmproxyDto) {
        UrlUtil.UrlEntity urlEntity = UrlUtil.parse(mitmproxyDto.getUrl());
        // 长赢
        if ("https://qieman.com/pmdj/v2/long-win/ca/assets-summary".equals(urlEntity.getUrl())
                && !ObjectUtils.isEmpty(mitmproxyDto.getBody())) {
            log.info("长赢 资产数据：{}", mitmproxyDto);
            log.info("解析前。。。");
            AssertBean assertBean = JSONUtil.toBean(mitmproxyDto.getBody(), AssertBean.class);
            log.info("解析后。。。");
            return;
        }

        if ("https://qieman.com/pmdj/v2/asset/ca/detail".equals(urlEntity.getUrl())
                && !ObjectUtils.isEmpty(mitmproxyDto.getBody())) {
            log.info("周周同行 资产数据：{}", mitmproxyDto);
        }
    }
}
