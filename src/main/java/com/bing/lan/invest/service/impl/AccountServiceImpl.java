package com.bing.lan.invest.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bing.lan.invest.dao.AccountDao;
import com.bing.lan.invest.domain.dto.AccountFundDetailDto;
import com.bing.lan.invest.domain.spider.qieman.AccountAssertBean;
import com.bing.lan.invest.domain.dto.FundDto;
import com.bing.lan.invest.domain.entity.Account;
import com.bing.lan.invest.domain.entity.Fund;
import com.bing.lan.invest.service.AccountFundDetailService;
import com.bing.lan.invest.service.AccountService;
import com.bing.lan.invest.service.FundService;

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
    public void updateAssert(String accountCode, AccountAssertBean assertBean) {
        Account account = getByAccountCode(accountCode);
        if (account == null) {
            throw new RuntimeException(accountCode + "账户不存在");
        }
        // 更新账户总资产
        account.setTotalAssert(new BigDecimal(assertBean.getTotalAsset()));
        account.setHoldingProfit(new BigDecimal(assertBean.getHoldingProfit()));
        account.setHoldingCost(account.getTotalAssert().subtract(account.getHoldingProfit()));
        account.setCustPart(new BigDecimal(assertBean.getTotalCustUnit()));
        account.setPlanPart(new BigDecimal(assertBean.getTotalPlanUnit()));
        dao.saveOrUpdate(account);

        accountFundDetailService.resetByAccountId(account.getId());

        for (AccountAssertBean.PlanAssetListDto planAssetListDto : assertBean.getPlanAssetList()) {
            for (AccountAssertBean.PlanAssetListDto.AssetListDto assetListDto : planAssetListDto.getAssetList()) {
                log.info("------- 基金数据：{}", assetListDto);
                AccountAssertBean.PlanAssetListDto.AssetListDto.FundDto listDtoFund = assetListDto.getFund();
                FundDto fundDto = new FundDto();
                fundDto.setFullName(listDtoFund.getFundName());
                fundDto.setCode(listDtoFund.getFundCode());
                fundDto.setName(assetListDto.getVariety());
                fundDto.setNav(new BigDecimal(listDtoFund.getNav()));
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
                accountFundDetailDto.setTotalAssert(new BigDecimal(assetListDto.getTotalAsset()));
                accountFundDetailDto.setHoldingProfit(new BigDecimal(assetListDto.getHoldingProfit()));
                accountFundDetailDto.setHoldingCost(accountFundDetailDto.getTotalAssert().subtract(accountFundDetailDto.getHoldingProfit()));
                accountFundDetailDto.setTotalShare(new BigDecimal(assetListDto.getTotalShare()));
                accountFundDetailDto.setCustUnitValue(new BigDecimal(assetListDto.getCustUnitValue()));
                accountFundDetailDto.setCustPart(new BigDecimal(assetListDto.getCustUnit()));
                accountFundDetailDto.setPlanPart(new BigDecimal(assetListDto.getPlanUnit()));
                accountFundDetailDto.setCleanFlag(accountFundDetailDto.getCustPart().compareTo(BigDecimal.ZERO) == 0);

                accountFundDetailService.saveOrUpdate(accountFundDetailDto);
            }
        }
    }
}
