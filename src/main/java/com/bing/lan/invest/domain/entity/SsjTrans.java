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
 * 随手记账户流水
 * </p>
 *
 * @author oopcoder
 * @since 2023-03-06
 */
@Data
@TableName("invest_ssj_trans")
@ApiModel(value = "SsjTrans对象", description = "随手记账户流水")
public class SsjTrans extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String tranId;

    private LocalDateTime tranTime;

    private BigDecimal amount;

    private String buyerAccount;

    private String buyerAccountId;

    private String sellerAccount;

    private String sellerAccountId;

    private Integer tranType;

    private String tranName;

    private String projectId;

    private String projectName;

    private String url;


}
