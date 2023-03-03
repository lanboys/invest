package com.bing.lan.invest.utils;

import com.bing.lan.invest.domain.dto.TurnoverDto;
import com.bing.lan.invest.domain.dto.TurnoversBean;
import com.bing.lan.invest.service.TurnoverService;

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
public class Spider {

    @Autowired
    TurnoverService turnoverService;

    public void spiderStart() {

        // 第一次请求目的是为了获取最早的时间
        // 1520405988000 2018-03-07 14:59:48 000 获取最早的时间
        // 1680278399999 2023-03-31 11:59:59 999 当月最后一天
        String beginTime = "1520405988000";
        String endTime = "1680278399999";
        String filterTurnoverIds = null;
        int pageSize = 100;
        while (true) {
            String request = OkHttpUtil.request(pageSize, beginTime, endTime, filterTurnoverIds);
            TurnoversBean turnoversBean = JSONUtil.toBean(request, TurnoversBean.class);
            List<TurnoversBean.ContentDto> content = turnoversBean.getContent();

            for (int i = 0; i < content.size(); i++) {
                TurnoversBean.ContentDto contentDto = content.get(i);
                if (i == content.size() - 1) {
                    endTime = contentDto.getAcceptTime().toString();
                    filterTurnoverIds = contentDto.getTurnoverId();
                }
                log.info("{}", contentDto);

                TurnoverDto turnoverDto = new TurnoverDto();
                BeanUtils.copyProperties(contentDto, turnoverDto);
                turnoverDto.setAcceptTime(LocalDateTimeUtil.of(contentDto.getAcceptTime()));
                turnoverDto.setTurnoverId(contentDto.getTurnoverId());
                try {
                    turnoverDto.setAmount(new BigDecimal(contentDto.getAmount()));
                } catch (Exception e) {
                }
                try {
                    turnoverDto.setBalance(new BigDecimal(contentDto.getBalance()));
                } catch (Exception e) {
                }
                turnoverDto.setIncomeFlag(contentDto.getIsIncome() ? 1 : 0);
                turnoverService.saveOrUpdate(turnoverDto);
            }

            if (content.size() == 0) {
                break;
            }

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
