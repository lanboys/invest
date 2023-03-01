package com.bing.lan.invest.domain.dto;

import lombok.Data;

/**
 * Created by lanbing at 2023/2/20 16:17
 */

@Data
public class MitmproxyDto {

    public String host;

    public Integer statusCode;

    public String url;

    public String body;

}
