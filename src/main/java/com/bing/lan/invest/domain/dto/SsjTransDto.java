package com.bing.lan.invest.domain.dto;

import com.bing.lan.invest.domain.entity.SsjTrans;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value = "SsjTransDto对象")
public class SsjTransDto extends SsjTrans {

}
