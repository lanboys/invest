package com.bing.lan.invest.domain.dto;

import com.bing.lan.invest.domain.entity.AccountFundDetail;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>
 * 投资基金详情
 * </p>
 *
 * @author oopcoder
 * @since 2023-03-02
 */
@Data
@ApiModel(value = "AccountFundDetailDto对象")
public class AccountFundDetailDto extends AccountFundDetail {

}
