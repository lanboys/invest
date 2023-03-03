package com.bing.lan.invest.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bing.lan.invest.base.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@TableName("invest_turnover")
@ApiModel(value = "Turnover对象", description = "投资基金详情")
public class Turnover extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String turnoverId;

    private LocalDateTime acceptTime;

    private BigDecimal amount;

    private BigDecimal balance;

    private String failType;

    private Integer incomeFlag;

    private String status;

    private String title;

    private String type;

    private String typeName;

    private String url;


}
