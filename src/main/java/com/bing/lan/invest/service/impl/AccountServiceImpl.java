package com.bing.lan.invest.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bing.lan.invest.dao.AccountDao;
import com.bing.lan.invest.domain.dto.AccountFundDetailDto;
import com.bing.lan.invest.domain.spider.qieman.AccountAssetBean;
import com.bing.lan.invest.domain.dto.FundDto;
import com.bing.lan.invest.domain.entity.Account;
import com.bing.lan.invest.domain.entity.Fund;
import com.bing.lan.invest.service.AccountFundDetailService;
import com.bing.lan.invest.service.AccountService;
import com.bing.lan.invest.service.FundService;
import com.bing.lan.invest.utils.BigDecimalUtil;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

import cn.hutool.core.date.LocalDateTimeUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 投资基金详情 服务实现类
 * </p>
 *
 * @author oopcoder
 * @since 2023-03-02
 */
@Slf4j
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDao dao;
    @Autowired
    FundService fundService;
    @Autowired
    AccountFundDetailService accountFundDetailService;

    @Override
    public Account getByAccountCode(String accountCode) {
        return dao.getOne(Wrappers.lambdaQuery(Account.class)
                .eq(Account::getAccountCode, accountCode), true);
    }

    @Override
    public void updateAsset(String accountCode, AccountAssetBean assertBean) {
        Account account = getByAccountCode(accountCode);
        if (account == null) {
            throw new RuntimeException(accountCode + "账户不存在");
        }
        // 更新账户总资产
        account.setTotalAsset(BigDecimalUtil.objToBigDecimal(assertBean.getTotalAsset()));
        account.setHoldingProfit(BigDecimalUtil.objToBigDecimal(assertBean.getHoldingProfit()));
        account.setHoldingCost(account.getTotalAsset().subtract(account.getHoldingProfit()));
        account.setCustPart(BigDecimalUtil.objToBigDecimal(assertBean.getTotalCustUnit()));
        account.setPlanPart(BigDecimalUtil.objToBigDecimal(assertBean.getTotalPlanUnit()));
        dao.saveOrUpdate(account);

        accountFundDetailService.resetByAccountId(account.getId());

        for (AccountAssetBean.PlanAssetListDto planAssetListDto : assertBean.getPlanAssetList()) {
            for (AccountAssetBean.PlanAssetListDto.AssetListDto assetListDto : planAssetListDto.getAssetList()) {
                log.info("------- 基金数据：{}", assetListDto);
                AccountAssetBean.PlanAssetListDto.AssetListDto.FundDto listDtoFund = assetListDto.getFund();
                FundDto fundDto = new FundDto();
                fundDto.setFullName(listDtoFund.getFundName());
                fundDto.setCode(listDtoFund.getFundCode());
                fundDto.setName(assetListDto.getVariety());
                fundDto.setNav(BigDecimalUtil.objToBigDecimal(listDtoFund.getNav()));
                fundDto.setNavDate(LocalDateTimeUtil.parseDate(listDtoFund.getNavDate(), DateTimeFormatter.ISO_LOCAL_DATE));

                Fund fund = fundService.saveOrUpdate(fundDto);

                AccountFundDetailDto accountFundDetailDto = new AccountFundDetailDto();
                BeanUtils.copyProperties(account, accountFundDetailDto);
                BeanUtils.copyProperties(fund, accountFundDetailDto);
                accountFundDetailDto.setId(null);
                accountFundDetailDto.setUpdateTime(null);
                accountFundDetailDto.setInsertTime(null);
                accountFundDetailDto.setAccountId(account.getId());
                accountFundDetailDto.setFundId(fund.getId());
                accountFundDetailDto.setFundNav(fund.getNav());

                // 更新单只基金资产
                accountFundDetailDto.setTotalAsset(BigDecimalUtil.objToBigDecimal(assetListDto.getTotalAsset()));
                accountFundDetailDto.setHoldingProfit(BigDecimalUtil.objToBigDecimal(assetListDto.getHoldingProfit()));
                accountFundDetailDto.setHoldingCost(accountFundDetailDto.getTotalAsset().subtract(accountFundDetailDto.getHoldingProfit()));
                accountFundDetailDto.setTotalShare(BigDecimalUtil.objToBigDecimal(assetListDto.getTotalShare()));
                accountFundDetailDto.setCustUnitValue(BigDecimalUtil.objToBigDecimal(assetListDto.getCustUnitValue()));
                accountFundDetailDto.setPlanUnitValue(BigDecimalUtil.objToBigDecimal(assetListDto.getPlanUnitValue()));
                accountFundDetailDto.setCustPart(BigDecimalUtil.objToBigDecimal(assetListDto.getCustUnit()));
                accountFundDetailDto.setPlanPart(BigDecimalUtil.objToBigDecimal(assetListDto.getPlanUnit()));
                accountFundDetailDto.setCleanFlag(accountFundDetailDto.getCustPart().compareTo(BigDecimal.ZERO) == 0);

                accountFundDetailService.saveOrUpdate(accountFundDetailDto);
            }
        }
    }
}
