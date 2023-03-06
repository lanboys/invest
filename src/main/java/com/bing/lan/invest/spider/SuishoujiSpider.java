package com.bing.lan.invest.spider;

import com.bing.lan.invest.domain.dto.TurnoverDto;
import com.bing.lan.invest.domain.spider.qieman.TurnoversBean;
import com.bing.lan.invest.domain.spider.sui.AccountBean;
import com.bing.lan.invest.service.TurnoverService;
import com.bing.lan.invest.utils.OkHttpUtil;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.math.*;
import java.util.*;
import java.util.concurrent.*;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by lanbing at 2023/3/3 17:54
 */

@Component
@Slf4j
public class SuishoujiSpider {

    @Autowired
    TurnoverService turnoverService;

    /**
     * 账号对账
     */
    public void accountStart() {
        int pageSize = 20;
        int pageIndex = 1;

        Integer pageCount = 0;
        int count = 0;

        while (true) {
            String request = OkHttpUtil.requestSuishoujiAccountTrans(pageSize, pageIndex);
            pageIndex++;

            AccountBean turnoversBean = JSONUtil.toBean(request, AccountBean.class);
            pageCount = turnoversBean.getPageCount();

            List<AccountBean.GroupsDto> groups = turnoversBean.getGroups();

            log.info("爬取数量：{}", groups.size());
            for (int i = 0; i < groups.size(); i++) {
                count++;
                AccountBean.GroupsDto contentDto = groups.get(i);
                log.info("流水数据 {}：{}", i,contentDto);
            }
            if (pageIndex > pageCount) {
                break;
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                log.error("睡眠异常", e);
            }
        }
        log.info("爬取结束, 本次爬取数量：{}", count);
    }
}
