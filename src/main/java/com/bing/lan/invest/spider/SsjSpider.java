package com.bing.lan.invest.spider;

import com.bing.lan.invest.domain.dto.SsjTransDto;
import com.bing.lan.invest.domain.spider.sui.SsjTransBean;
import com.bing.lan.invest.service.SsjTransService;
import com.bing.lan.invest.service.TurnoverService;
import com.bing.lan.invest.utils.OkHttpUtil;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
public class SsjSpider {

    @Autowired
    SsjTransService ssjTransService;

    /**
     * 账号对账
     */
    public void accountStart() {
        int pageSize = 20;
        int pageIndex = 1;

        int pageCount;
        int count = 0;

        while (true) {
            String request = OkHttpUtil.requestSuishoujiAccountTrans(pageSize, pageIndex);
            pageIndex++;

            SsjTransBean turnoversBean = JSONUtil.toBean(request, SsjTransBean.class);
            pageCount = turnoversBean.getPageCount();

            List<SsjTransBean.GroupsDto> groups = turnoversBean.getGroups();

            log.info("爬取数量：{}", groups.size());
            for (int i = 0; i < groups.size(); i++) {
                count++;
                SsjTransBean.GroupsDto contentDto = groups.get(i);
                log.info("流水数据 {}：{}", i, contentDto);

                List<SsjTransBean.GroupsDto.ListDto> list = contentDto.getList();
                for (SsjTransBean.GroupsDto.ListDto listDto : list) {
                    SsjTransBean.GroupsDto.ListDto.DateDto date = listDto.getDate();
                    SsjTransDto ssjTransDto = new SsjTransDto();
                    BeanUtils.copyProperties(listDto, ssjTransDto);
                    ssjTransDto.setTranTime(LocalDateTimeUtil.of(date.getTime()));
                    ssjTransDto.setAmount(new BigDecimal(listDto.getItemAmount()));

                    ssjTransDto.setBuyerAccount(listDto.getBuyerAcount());
                    ssjTransDto.setBuyerAccountId(listDto.getBuyerAcountId());
                    ssjTransDto.setSellerAccount(listDto.getSellerAcount());
                    ssjTransDto.setSellerAccountId(listDto.getSellerAcountId());
                    ssjTransService.saveOrUpdate(ssjTransDto);
                }
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
