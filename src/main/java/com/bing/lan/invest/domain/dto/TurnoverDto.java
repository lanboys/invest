package com.bing.lan.invest.domain.dto;

import com.bing.lan.invest.domain.entity.Turnover;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>
 * 投资基金详情
 * </p>
 *
 * @author oopcoder
 * @since 2023-03-03
 */
@Data
@ApiModel(value = "TurnoverDto对象")
public class TurnoverDto extends Turnover {

}
