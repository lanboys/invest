package com.bing.lan.invest.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bing.lan.invest.dao.ProfitRateDao;
import com.bing.lan.invest.domain.dto.ProfitRateDto;
import com.bing.lan.invest.domain.entity.ProfitRate;
import com.bing.lan.invest.service.ProfitRateService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author oopcoder
 * @since 2023-03-07
 */
@Service
@Transactional
public class ProfitRateServiceImpl implements ProfitRateService {

    @Autowired
    ProfitRateDao dao;

    @Override
    public ProfitRate saveOrUpdate(ProfitRateDto dto) {
        LambdaUpdateWrapper<ProfitRate> update = Wrappers.lambdaUpdate(ProfitRate.class)
                .eq(ProfitRate::getProfitDate, dto.getProfitDate());

        dao.saveOrUpdate(dto, update);
        return dao.getOne(Wrappers.lambdaQuery(ProfitRate.class)
                .eq(ProfitRate::getProfitDate, dto.getProfitDate()));
    }
}
