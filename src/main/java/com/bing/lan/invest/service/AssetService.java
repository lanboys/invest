package com.bing.lan.invest.service;

import com.bing.lan.invest.domain.dto.AssetDto;
import com.bing.lan.invest.domain.entity.Asset;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author oopcoder
 * @since 2023-03-07
 */
public interface AssetService {

    public Asset saveOrUpdate(AssetDto dto);

}
