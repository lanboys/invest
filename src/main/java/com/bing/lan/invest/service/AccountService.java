package com.bing.lan.invest.service;

import com.bing.lan.invest.domain.spider.qieman.AccountAssetBean;
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

    public void updateAsset(String accountCode, AccountAssetBean assertBean);

}
