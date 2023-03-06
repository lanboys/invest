package com.bing.lan.invest.domain.spider.sui;

import java.util.*;

import lombok.Data;

/**
 * Created by lanbing at 2023/3/6 9:50
 */

@Data
public class SsjTransBean {

    private Double income;
    private Integer pageCount;
    private Integer pageNo;
    private Double payout;
    private List<GroupsDto> groups;

    @Data
    public static class GroupsDto {

        private Integer income;
        private Integer payout;
        private List<ListDto> list;

        @Data
        public static class ListDto {

            private Long account;
            private String buyerAcount;
            private String buyerAcountId;
            private String categoryIcon;
            private Integer categoryId;
            private String categoryName;
            private String content;
            private Integer currencyAmount;
            private DateDto date;
            private Integer imgId;
            private String itemAmount;
            private Integer memberId;
            private String memberName;
            private String memo;
            private String projectId;
            private String projectName;
            private String relation;
            private String sId;
            private String sellerAcount;
            private String sellerAcountId;
            private String tranId;
            private String tranName;
            private Integer tranType;
            private Integer transferStoreId;
            private String url;

            @Data
            public static class DateDto {

                private Integer date;
                private Integer day;
                private Integer hours;
                private Integer minutes;
                private Integer month;
                private Integer seconds;
                private Long time;
                private Integer timezoneOffset;
                private Integer year;
            }
        }
    }
}
