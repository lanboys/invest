package com.bing.lan.invest.service;

import com.bing.lan.invest.domain.dto.AssertBean;
import com.bing.lan.invest.domain.dto.MitmproxyDto;
import com.bing.lan.invest.domain.entity.Account;

/**
 * <p>
 * 投资基金详情 服务类
 * </p>
 *
 * @author oopcoder
 * @since 2023-03-02
 */
public interface AccountService {

    public Account getByAccountCode(String accountCode);

    public void updateAssert(String accountCode, AssertBean assertBean);

}
