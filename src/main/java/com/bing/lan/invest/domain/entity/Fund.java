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
 * 投资基金详情
 * </p>
 *
 * @author oopcoder
 * @since 2023-03-02
 */
@Data
@TableName("invest_fund")
@ApiModel(value = "Fund对象", description = "投资基金详情")
public class Fund extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("行业")
    private String sector;

    @ApiModelProperty("基金简称")
    private String name;

    @ApiModelProperty("基金全称")
    private String fullName;

    @ApiModelProperty("基金代码")
    private String code;

    @ApiModelProperty("资产净值 net asset value")
    private BigDecimal nav;

    @ApiModelProperty("净值日期")
    private LocalDate navDate;


}
