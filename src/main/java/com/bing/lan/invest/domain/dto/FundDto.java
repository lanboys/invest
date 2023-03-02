package com.bing.lan.invest.domain.dto;

import com.bing.lan.invest.domain.entity.Fund;
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
@ApiModel(value = "FundDto对象")
public class FundDto extends Fund {

}
