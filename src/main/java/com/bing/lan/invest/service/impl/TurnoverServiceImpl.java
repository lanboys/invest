package com.bing.lan.invest.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bing.lan.invest.dao.TurnoverDao;
import com.bing.lan.invest.domain.dto.TurnoverDto;
import com.bing.lan.invest.domain.dto.TurnoverDto;
import com.bing.lan.invest.domain.entity.Turnover;
import com.bing.lan.invest.domain.entity.Turnover;
import com.bing.lan.invest.service.TurnoverService;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 投资基金详情 服务实现类
 * </p>
 *
 * @author oopcoder
 * @since 2023-03-03
 */
@Service
@Transactional
public class TurnoverServiceImpl implements TurnoverService {

    @Autowired
    TurnoverDao dao;

    @Override
    public Turnover saveOrUpdate(TurnoverDto dto) {
        LambdaUpdateWrapper<Turnover> update = Wrappers.lambdaUpdate(Turnover.class)
                .eq(Turnover::getTurnoverId, dto.getTurnoverId());

        dao.saveOrUpdate(dto, update);
        return dao.getOne(Wrappers.lambdaQuery(Turnover.class)
                .eq(Turnover::getTurnoverId, dto.getTurnoverId()));
    }
}
