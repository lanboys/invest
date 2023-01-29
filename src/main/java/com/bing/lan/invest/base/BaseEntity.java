package com.bing.lan.invest.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public abstract class BaseEntity implements Serializable {

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("创建时间")
    @TableField(value = "insert_time")
    private LocalDateTime insertTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

}

