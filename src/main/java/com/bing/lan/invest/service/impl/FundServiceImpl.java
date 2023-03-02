package com.bing.lan.invest.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.bing.lan.invest.dao.FundDao;
import com.bing.lan.invest.domain.dto.FundDto;
import com.bing.lan.invest.domain.entity.Fund;
import com.bing.lan.invest.service.FundService;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Wrapper;

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
public class FundServiceImpl implements FundService {

    @Autowired
    FundDao dao;

    @Override
    public Fund saveOrUpdate(FundDto fundDto) {
        LambdaUpdateWrapper<Fund> update = Wrappers.lambdaUpdate(Fund.class)
                .eq(Fund::getCode, fundDto.getCode());

        dao.saveOrUpdate(fundDto, update);
        return dao.getOne(Wrappers.lambdaQuery(Fund.class).eq(Fund::getCode, fundDto.getCode()));
    }
}
