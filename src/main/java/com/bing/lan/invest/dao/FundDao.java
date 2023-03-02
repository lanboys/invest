package com.bing.lan.invest.dao;

import com.bing.lan.invest.domain.entity.Fund;
import com.bing.lan.invest.mapper.FundMapper;

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
public class FundDao extends BaseDao<FundMapper,Fund> {

}
