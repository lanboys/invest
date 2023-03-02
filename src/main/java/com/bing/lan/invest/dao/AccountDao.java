package com.bing.lan.invest.dao;

import com.bing.lan.invest.domain.entity.Account;
import com.bing.lan.invest.mapper.AccountMapper;

import com.bing.lan.invest.base.BaseDao;
import org.springframework.stereotype.Repository;


/**
 * <p>
 * 投资基金详情 dao
 * </p>
 *
 * @author oopcoder
 * @since 2023-03-02
 */
@Repository
public class AccountDao extends BaseDao<AccountMapper,Account> {

}
