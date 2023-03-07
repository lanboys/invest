package com.bing.lan.invest.service;

import com.bing.lan.invest.domain.dto.ProfitRateDto;
import com.bing.lan.invest.domain.entity.ProfitRate;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author oopcoder
 * @since 2023-03-07
 */
public interface ProfitRateService {

    public ProfitRate saveOrUpdate(ProfitRateDto dto);

}
