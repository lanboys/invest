package com.bing.lan.invest.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.bing.lan.invest.dao.AccountFundDetailDao;
import com.bing.lan.invest.domain.dto.AccountFundDetailDto;
import com.bing.lan.invest.domain.dto.FundDto;
import com.bing.lan.invest.domain.entity.AccountFundDetail;
import com.bing.lan.invest.domain.entity.Fund;
import com.bing.lan.invest.service.AccountFundDetailService;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 投资基金详情 服务实现类
 * </p>
 *
 * @author oopcoder
 * @since 2023-03-02
 */
@Service
@Transactional
public class AccountFundDetailServiceImpl implements AccountFundDetailService {

    @Autowired
    AccountFundDetailDao dao;

    @Override
    public AccountFundDetail saveOrUpdate(AccountFundDetailDto dto) {
        LambdaUpdateWrapper<AccountFundDetail> update = Wrappers.lambdaUpdate(AccountFundDetail.class)
                .eq(AccountFundDetail::getCode, dto.getCode())
                .eq(AccountFundDetail::getAccountId, dto.getAccountId());

        dao.saveOrUpdate(dto, update);
        return dao.getOne(Wrappers.lambdaQuery(AccountFundDetail.class)
                .eq(AccountFundDetail::getCode, dto.getCode())
                .eq(AccountFundDetail::getAccountId, dto.getAccountId()));
    }

    @Override
    public boolean resetByAccountId(Integer accountId) {
        LambdaUpdateWrapper<AccountFundDetail> update = Wrappers.lambdaUpdate(AccountFundDetail.class)
                .eq(AccountFundDetail::getAccountId, accountId)
                .set(AccountFundDetail::getCleanFlag, true)
                .set(AccountFundDetail::getTotalAsset, 0)
                .set(AccountFundDetail::getHoldingProfit, 0)
                .set(AccountFundDetail::getHoldingCost, 0)
                .set(AccountFundDetail::getCustPart, 0)
                .set(AccountFundDetail::getPlanPart, 0)
                .set(AccountFundDetail::getTotalShare, 0)
                .set(AccountFundDetail::getFundNav, 0)
                .set(AccountFundDetail::getCustUnitValue, 0);

        return dao.update(update);
    }
}
