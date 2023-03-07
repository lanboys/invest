package com.bing.lan.invest.domain.spider.qieman;

import lombok.Data;

/**
 * Created by lanbing at 2023/3/7 9:06
 */
@Data
public class ProfitRateDataBean {

    private Long profitDate;
    private String accCost;
    private String totalAsset;
    private Boolean isOpeningAssetsDay;
}
