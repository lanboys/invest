package com.bing.lan.invest.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bing.lan.invest.dao.AssetDao;
import com.bing.lan.invest.domain.dto.AssetDto;
import com.bing.lan.invest.domain.entity.Asset;
import com.bing.lan.invest.service.AssetService;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author oopcoder
 * @since 2023-03-07
 */
@Service
@Transactional
public class AssetServiceImpl implements AssetService {

    @Autowired
    AssetDao dao;

    @Override
    public Asset saveOrUpdate(AssetDto dto) {
        LambdaUpdateWrapper<Asset> update = Wrappers.lambdaUpdate(Asset.class)
                .eq(Asset::getProfitDate, dto.getProfitDate());

        dao.saveOrUpdate(dto, update);
        return dao.getOne(Wrappers.lambdaQuery(Asset.class)
                .eq(Asset::getProfitDate, dto.getProfitDate()));
    }
}
