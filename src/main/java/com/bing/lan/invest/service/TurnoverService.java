package com.bing.lan.invest.service;

import com.bing.lan.invest.domain.dto.AccountFundDetailDto;
import com.bing.lan.invest.domain.dto.TurnoverDto;
import com.bing.lan.invest.domain.entity.AccountFundDetail;
import com.bing.lan.invest.domain.entity.Turnover;

/**
 * <p>
 * 投资基金详情 服务类
 * </p>
 *
 * @author oopcoder
 * @since 2023-03-03
 */
public interface TurnoverService {

    public Turnover saveOrUpdate(TurnoverDto dto);

}
