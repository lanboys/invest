package com.bing.lan.invest.service;

import com.bing.lan.invest.domain.dto.FundDto;
import com.bing.lan.invest.domain.entity.Fund;

/**
 * <p>
 * 投资基金详情 服务类
 * </p>
 *
 * @author oopcoder
 * @since 2023-03-02
 */
public interface FundService {

    public Fund saveOrUpdate(FundDto fundDto);

}
