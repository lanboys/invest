package com.bing.lan.invest.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bing.lan.invest.dao.SsjTransDao;
import com.bing.lan.invest.domain.dto.SsjTransDto;
import com.bing.lan.invest.domain.dto.TurnoverDto;
import com.bing.lan.invest.domain.entity.SsjTrans;
import com.bing.lan.invest.domain.entity.Turnover;
import com.bing.lan.invest.service.SsjTransService;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 随手记账户流水 服务实现类
 * </p>
 *
 * @author oopcoder
 * @since 2023-03-06
 */
@Service
@Transactional
public class SsjTransServiceImpl implements SsjTransService {

    @Autowired
    SsjTransDao dao;

    @Override
    public SsjTrans saveOrUpdate(SsjTransDto dto) {
        LambdaUpdateWrapper<SsjTrans> update = Wrappers.lambdaUpdate(SsjTrans.class)
                .eq(SsjTrans::getTranId, dto.getTranId());

        dao.saveOrUpdate(dto, update);
        return dao.getOne(Wrappers.lambdaQuery(SsjTrans.class)
                .eq(SsjTrans::getTranId, dto.getTranId()));
    }
}
