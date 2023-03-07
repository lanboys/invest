package com.bing.lan.invest.domain.spider.qieman;

import lombok.Data;

/**
 * Created by lanbing at 2023/3/7 9:26
 * 资产曲线数据
 */

@Data
public class AssetDataBean {

    private Long profitDate;
    private String accProfit;
    private String totalAsset;
    private String cumulativeCost;
    private String inputAmount;
    private String outputAmount;
}
