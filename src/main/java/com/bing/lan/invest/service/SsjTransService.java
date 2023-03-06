package com.bing.lan.invest.service;

import com.bing.lan.invest.domain.dto.SsjTransDto;
import com.bing.lan.invest.domain.entity.SsjTrans;

/**
 * <p>
 * 随手记账户流水 服务类
 * </p>
 *
 * @author oopcoder
 * @since 2023-03-06
 */
public interface SsjTransService {

    public SsjTrans saveOrUpdate(SsjTransDto dto);

}
