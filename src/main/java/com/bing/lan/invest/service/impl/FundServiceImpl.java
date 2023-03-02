package com.bing.lan.invest.service.impl;

import com.bing.lan.invest.dao.FundDao;
import com.bing.lan.invest.service.FundService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 投资基金详情 服务实现类
 * </p>
 *
 * @author oopcoder
 * @since 2023-03-02
 */
@Service
@Transactional
public class FundServiceImpl implements FundService {

    @Autowired
    FundDao dao;

}
