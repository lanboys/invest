package com.bing.lan.invest.spider;

import com.bing.lan.invest.domain.dto.TurnoverDto;
import com.bing.lan.invest.domain.dto.TurnoversBean;
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
        // 第一次请求目的是为了获取最早的时间
        // 1520405988000 2018-03-07 14:59:48 000 获取最早的时间
        // 1680278399999 2023-03-31 11:59:59 999 当月最后一天
        String beginTime = "1520405988000";
        String endTime = null;
        String filterTurnoverIds = null;
        int pageSize = 1;// 第一次请求一条
        int count = 0;

        while (true) {

            String request = OkHttpUtil.requestQiemanTurnovers(pageSize, beginTime, endTime, filterTurnoverIds);
            pageSize = 200;
            TurnoversBean turnoversBean = JSONUtil.toBean(request, TurnoversBean.class);
            List<TurnoversBean.ContentDto> content = turnoversBean.getContent();
            log.info("爬取数量：{}", content.size());
            for (int i = 0; i < content.size(); i++) {
                count++;
                TurnoversBean.ContentDto contentDto = content.get(i);
                if (i == content.size() - 1) {
                    endTime = contentDto.getAcceptTime().toString();
                    filterTurnoverIds = contentDto.getTurnoverId();
                }
                log.info("流水数据：{}", contentDto);
                TurnoverDto turnoverDto = new TurnoverDto();
                BeanUtils.copyProperties(contentDto, turnoverDto);
                turnoverDto.setAcceptTime(LocalDateTimeUtil.of(contentDto.getAcceptTime()));
                turnoverDto.setTurnoverId(contentDto.getTurnoverId());

                if (!ObjectUtils.isEmpty(contentDto.getAmount())) {
                    turnoverDto.setAmount(new BigDecimal(contentDto.getAmount()));
                }
                if (!ObjectUtils.isEmpty(contentDto.getBalance())) {
                    turnoverDto.setBalance(new BigDecimal(contentDto.getBalance()));
                }
                turnoverDto.setIncomeFlag(contentDto.getIsIncome() ? 1 : 0);
                turnoverService.saveOrUpdate(turnoverDto);
            }

            if (content.size() == 0) {
                break;
            }

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                log.error("睡眠异常", e);
            }
        }
        log.info("爬取结束, 本次爬取数量：{}", count);
    }
}
