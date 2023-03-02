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
@TableName("invest_account_fund_detail")
@ApiModel(value = "AccountFundDetail对象", description = "投资基金详情")
public class AccountFundDetail extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("账户id")
    private Integer accountId;

    @ApiModelProperty("是否清仓：0 否 1 是")
    private Boolean cleanFlag;

    @ApiModelProperty("平台(冗余)")
    private String platformName;

    @ApiModelProperty("投资账户(冗余)")
    private String accountName;

    @ApiModelProperty("投资组合(冗余)")
    private String portfolioName;

    @ApiModelProperty("基金id")
    private Integer fundId;

    @ApiModelProperty("基金简称(冗余)")
    private String name;

    @ApiModelProperty("基金代码(冗余)")
    private String code;

    @ApiModelProperty("行业(冗余)")
    private String sector;

    @ApiModelProperty("持仓金额")
    private BigDecimal totalAssert;

    @ApiModelProperty("持仓收益")
    private BigDecimal holdingProfit;

    @ApiModelProperty("持仓成本")
    private BigDecimal holdingCost;

    @ApiModelProperty("持有份数")
    private BigDecimal custPart;

    @ApiModelProperty("计划份数")
    private BigDecimal planPart;

    @ApiModelProperty("持有份额")
    private BigDecimal totalShare;

    @ApiModelProperty("持有单价")
    private BigDecimal custUnitValue;

    @ApiModelProperty("基金净值(冗余)")
    private BigDecimal fundNav;


}
