package com.bing.lan.invest.service;

import com.bing.lan.invest.domain.dto.AccountFundDetailDto;
import com.bing.lan.invest.domain.entity.AccountFundDetail;

/**
 * <p>
 * 投资基金详情 服务类
 * </p>
 *
 * @author oopcoder
 * @since 2023-03-02
 */
public interface AccountFundDetailService {

    public AccountFundDetail saveOrUpdate(AccountFundDetailDto dto);

    public boolean resetByAccountId(Integer accountId);

}
