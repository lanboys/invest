package com.bing.lan.invest.domain.spider.qieman;

import java.util.List;

import lombok.Data;

/**
 * Created by lanbing at 2023/1/29 11:47
 *
 * 账户购买基金详情数据
 */

@Data
public class AccountAssertBean {

    private Integer totalPlanUnit;
    private String totalCustUnit;
    private String totalAsset;
    private String shareAsset;
    private String previousProfit;
    private String accumulatedProfit;
    private String holdingProfit;
    private Long profitDate;
    private String latestDailyProfit;
    private String latestDailyProfitRate;
    private String accumulatedProfitRate;
    private String holdingProfitRate;
    private ProfitProgressDto profitProgress;
    private String xirrAnnualizedRate;
    private Integer holdingDays;
    private Integer afterDays;
    private Long firstNotCancelledOrderDate;
    private List<PlanAssetListDto> planAssetList;

    @Data
    public static class ProfitProgressDto {

        private Integer total;
        private Integer updated;
        private String status;
        private String msg;
    }

    @Data
    public static class PlanAssetListDto {

        private String className;
        private String classCode;
        private String custUnit;
        private String availUnit;
        private String percent;
        private List<AssetListDto> assetList;

        @Data
        public static class AssetListDto {

            private FundDto fund;
            private String shareType;
            private String variety;
            private String strategyType;
            private String nav;
            private Long navDate;
            private Boolean canBuy;
            private Boolean canRedeem;
            private Integer planUnit;
            private String custUnit;
            private String availUnit;
            private String totalShare;
            private String availShare;
            private String totalAsset;
            private String availShareAsset;
            private String previousProfit;
            private String accProfitRate;
            private String accumulatedProfit;
            private String previousProfitRate;
            private String latestDailyProfit;
            private String latestDailyProfitRate;
            private String accumulatedProfitRate;
            private String percent;
            private Boolean isCash;
            private Boolean hasNotCancelledOrders;
            private String profit;
            private Object recentDividend;
            private Object recentSplit;
            private String planUnitValue;
            private String custUnitValue;
            private String holdingProfit;
            private String holdingProfitRate;
            private String dailyReturn;
            private AggrAssetDto aggrAsset;

            @Data
            public static class FundDto {

                private String fundCode;
                private String fundName;
                private String fundInvestType;
                private Boolean isQdii;
                private Boolean canBuy;
                private Boolean canRedeem;
                private Boolean onSale;
                private String nav;
                private String navDate;
                private Object personalHighestBuyAmount;
            }

            @Data
            public static class AggrAssetDto {

                private String accProfit;
                private String accProfitRate;
                private String custUnitValue;
                private String holdingProfitRate;
                private String nav;
                private String dailyReturn;
                private Long navDate;
                private Long assetDate;
            }
        }
    }
}
