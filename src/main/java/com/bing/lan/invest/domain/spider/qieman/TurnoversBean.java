package com.bing.lan.invest.domain.spider.qieman;

import java.util.List;

import lombok.Data;

/**
 * Created by oopcoder at 2023/3/2 22:25 .
 */
@Data
public class TurnoversBean {

    private List<ContentDto> content;
    private CursorParamDto cursorParam;
    private Long turnoverStartTime;

    @Data
    public static class CursorParamDto {

        private String filterTurnoverIds;
    }

    @Data
    public static class ContentDto {

        private Long acceptTime;
        private String amount;
        private String balance;
        private String bankCardId;
        private Boolean canOpen;
        private String failType;
        private Boolean isIncome;
        private String status;
        private String statusName;
        private String title;
        private String turnoverId;
        private String type;
        private String typeName;
        private String url;
    }
}
