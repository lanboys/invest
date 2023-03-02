package com.bing.lan.invest.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bing.lan.invest.base.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@TableName("invest_account")
@ApiModel(value = "Account对象", description = "投资基金详情")
public class Account extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("且慢平台id")
    private String accountCode;

    @ApiModelProperty("平台")
    private String platformName;

    @ApiModelProperty("投资账户名称")
    private String accountName;

    @ApiModelProperty("投资组合")
    private String portfolioName;

    @ApiModelProperty("持仓金额")
    private BigDecimal totalAssert;

    @ApiModelProperty("持仓收益")
    private BigDecimal holdingProfit;

    @ApiModelProperty("持仓成本")
    private BigDecimal holdingCost;

    @ApiModelProperty("持有份数")
    private BigDecimal custPart;

    @ApiModelProperty("计划份数")
    private Long planPart;


}
