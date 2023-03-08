package com.bing.lan.invest.service.impl;

import com.bing.lan.invest.domain.dto.AssetDto;
import com.bing.lan.invest.domain.dto.ProfitRateDto;
import com.bing.lan.invest.domain.spider.qieman.AccountAssetBean;
import com.bing.lan.invest.domain.dto.MitmproxyDto;
import com.bing.lan.invest.domain.dto.TurnoverDto;
import com.bing.lan.invest.domain.spider.qieman.AssetDataBean;
import com.bing.lan.invest.domain.spider.qieman.ProfitRateDataBean;
import com.bing.lan.invest.domain.spider.qieman.TurnoversBean;
import com.bing.lan.invest.service.AccountService;
import com.bing.lan.invest.service.AssetService;
import com.bing.lan.invest.service.MitmproxyService;
import com.bing.lan.invest.service.ProfitRateService;
import com.bing.lan.invest.service.TurnoverService;
import com.bing.lan.invest.utils.BigDecimalUtil;
import com.bing.lan.invest.utils.UrlUtil;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.*;
import java.text.SimpleDateFormat;
import java.util.List;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by lanbing at 2023/3/1 18:28
 */

@Service
@Slf4j
@Transactional
public class MitmproxyServiceImpl implements MitmproxyService {

    @Autowired
    AccountService accountService;
    @Autowired
    TurnoverService turnoverService;
    @Autowired
    AssetService assetService;
    @Autowired
    ProfitRateService profitRateService;

    @Override
    @Async
    public void parseMitmproxyData(MitmproxyDto mitmproxyDto) {
        if (ObjectUtils.isEmpty(mitmproxyDto.getBody())) {
            log.info("空数据。。");
            return;
        }
        UrlUtil.UrlEntity urlEntity = UrlUtil.parse(mitmproxyDto.getUrl());
        // log.info("抓包数据：{}", mitmproxyDto);

        // 长赢
        if ("https://qieman.com/pmdj/v2/long-win/ca/assets-summary".equals(urlEntity.getUrl())) {
            AccountAssetBean assertBean = JSONUtil.toBean(mitmproxyDto.getBody(), AccountAssetBean.class);
            accountService.updateAsset(urlEntity.getParam("capitalAccountId"), assertBean);
            return;
        }

        // 周周同行
        if ("https://qieman.com/pmdj/v2/asset/ca/detail".equals(urlEntity.getUrl())) {
        }

        if ("https://api.qieman.com/pmdj/v2/asset/curve/profit-rate".equals(urlEntity.getUrl())) {
            List<ProfitRateDataBean> profitRateDataBeans = JSONUtil.toList(mitmproxyDto.getBody(), ProfitRateDataBean.class);
            for (ProfitRateDataBean profitRateDataBean : profitRateDataBeans) {
                log.info("收益率曲线数据：{}", profitRateDataBean);
                ProfitRateDto dto = new ProfitRateDto();
                dto.setProfitDate(LocalDateTimeUtil.of(profitRateDataBean.getProfitDate() * 1000).toLocalDate());
                dto.setAccCost(BigDecimalUtil.objToBigDecimal(profitRateDataBean.getAccCost()));
                dto.setTotalAsset(BigDecimalUtil.objToBigDecimal(profitRateDataBean.getTotalAsset()));
                dto.setOpeningAssetsDay(profitRateDataBean.getIsOpeningAssetsDay());
                profitRateService.saveOrUpdate(dto);
            }
        }

        if ("https://api.qieman.com/pmdj/v2/asset/curve/asset-data".equals(urlEntity.getUrl())) {
            List<AssetDataBean> assetDataBeans = JSONUtil.toList(mitmproxyDto.getBody(), AssetDataBean.class);
            for (AssetDataBean assetDataBean : assetDataBeans) {
                log.info("资产曲线数据：{}", assetDataBean);
                AssetDto dto = new AssetDto();
                dto.setProfitDate(LocalDateTimeUtil.of(assetDataBean.getProfitDate() * 1000).toLocalDate());
                dto.setAccProfit(BigDecimalUtil.objToBigDecimal(assetDataBean.getAccProfit()));
                dto.setTotalAsset(BigDecimalUtil.objToBigDecimal(assetDataBean.getTotalAsset()));
                dto.setCumulativeCost(BigDecimalUtil.objToBigDecimal(assetDataBean.getCumulativeCost()));
                dto.setInputAmount(BigDecimalUtil.objToBigDecimal(assetDataBean.getInputAmount()));
                dto.setOutputAmount(BigDecimalUtil.objToBigDecimal(assetDataBean.getOutputAmount()));
                assetService.saveOrUpdate(dto);
            }
        }

        if ("https://qieman.com/pmdj/v2/wallet/turnovers".equals(urlEntity.getUrl())) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String beginTime = urlEntity.getParam("beginTime");
            if (!ObjectUtils.isEmpty(beginTime)) {
                log.info("开始时间：{}", format.format(Long.parseLong(beginTime)));
            }
            String endTime = urlEntity.getParam("endTime");
            if (!ObjectUtils.isEmpty(endTime)) {
                log.info("结束时间：{}", format.format(Long.parseLong(endTime)));
            }
            TurnoversBean turnoversBean = JSONUtil.toBean(mitmproxyDto.getBody(), TurnoversBean.class);
            if (!ObjectUtils.isEmpty(turnoversBean.getTurnoverStartTime())) {
                log.info("turnoverStartTime：{}", format.format(turnoversBean.getTurnoverStartTime()));
            }

            List<TurnoversBean.ContentDto> content = turnoversBean.getContent();
            for (TurnoversBean.ContentDto contentDto : content) {
                // log.info("账单数据：{}", contentDto);
                log.info("acceptTime：{}", format.format(contentDto.getAcceptTime()));
                log.info("turnoverId：{}", contentDto.getTurnoverId());
                TurnoverDto turnoverDto = new TurnoverDto();
                BeanUtils.copyProperties(contentDto, turnoverDto);
                turnoverDto.setAcceptTime(LocalDateTimeUtil.of(contentDto.getAcceptTime()));
                turnoverDto.setTurnoverId(contentDto.getTurnoverId());
                turnoverDto.setAmount(BigDecimalUtil.objToBigDecimal(contentDto.getAmount()));
                turnoverDto.setBalance(BigDecimalUtil.objToBigDecimal(contentDto.getBalance()));
                turnoverDto.setIncomeFlag(contentDto.getIsIncome() ? 1 : 0);
                turnoverService.saveOrUpdate(turnoverDto);
            }
        }
    }
}
