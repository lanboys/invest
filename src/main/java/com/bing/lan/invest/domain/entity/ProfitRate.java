package com.bing.lan.invest.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bing.lan.invest.base.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author oopcoder
 * @since 2023-03-07
 */
@Data
@TableName("invest_profit_rate")
@ApiModel(value = "ProfitRate对象", description = "")
public class ProfitRate extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private LocalDate profitDate;

    private BigDecimal accCost;

    private BigDecimal totalAsset;

    private Boolean openingAssetsDay;


}
