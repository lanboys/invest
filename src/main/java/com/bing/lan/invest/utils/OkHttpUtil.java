package com.bing.lan.invest.utils;

import org.springframework.util.ObjectUtils;

import java.io.*;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by lanbing at 2023/3/3 17:45
 */

public class OkHttpUtil {

    public static OkHttpClient client = new OkHttpClient().newBuilder().build();

    public static String requestSuishoujiAccountTrans(Integer pageSize, Integer pageIndex) {
        String content = "opt=bills&interval=-1&onePageNum=" + pageSize + "&bids=423019153534&page=" + pageIndex;
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");
        RequestBody body = RequestBody.create(mediaType, content);
        Request request = new Request.Builder()
                .url("https://www.sui.com/account/account.rmi")
                .method("POST", body)
                .addHeader("authority", "www.sui.com")
                .addHeader("accept", "*/*")
                .addHeader("accept-language", "zh-CN,zh;q=0.9")
                .addHeader("content-type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("cookie", "__vistor=27E3DBE71gtt76xum; SESSION_COOKIE=d9e21fbbd853e2d89543561dfe485965; __spm_bid=a173a197b68bsb7apbcdb9b01emf1f46; Hm_lvt_3db4e52bb5797afe0faaa2fde5c96ea4=1678064712; __utmc=121176714; __utmz=121176714.1678064713.1.1.utmcsr=cn.bing.com|utmccn=(referral)|utmcmd=referral|utmcct=/; __nick=lan_bing2013%40163.com; _bookTabSwitchList=1a747bbbd47f95bf70d78bdf7b00061f|0|0&; SESSION=ea36dead-44e4-4db4-8847-b7efff03303c; __utma=121176714.1803278298.1678064713.1678067041.1678072399.3; __utmt=1; Hm_lpvt_3db4e52bb5797afe0faaa2fde5c96ea4=1678072426; __utmb=121176714.3.9.1678072430629")
                .addHeader("origin", "https://www.sui.com")
                .addHeader("referer", "https://www.sui.com/account/account.do")
                .addHeader("sec-ch-ua", "\".Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"103\", \"Chromium\";v=\"103\"")
                .addHeader("sec-ch-ua-mobile", "?0")
                .addHeader("sec-ch-ua-platform", "\"Windows\"")
                .addHeader("sec-fetch-dest", "empty")
                .addHeader("sec-fetch-mode", "cors")
                .addHeader("sec-fetch-site", "same-origin")
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36")
                .addHeader("x-requested-with", "XMLHttpRequest")
                .build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String requestQiemanTurnovers(Integer pageSize, String beginTime, String endTime, String filterTurnoverIds) {

        String url = "https://qieman.com/pmdj/v2/wallet/turnovers?pageSize=" + pageSize;

        // 非第一次请求
        if (!ObjectUtils.isEmpty(beginTime) && !ObjectUtils.isEmpty(endTime)) {
            url = url + "&beginTime=" + beginTime + "&endTime=" + endTime + "&paymentMethodId=92dqwqxl3g38&filterTurnoverIds=" + filterTurnoverIds;
        }
        // MediaType mediaType = MediaType.parse("text/plain");
        // RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "zh-CN,zh;q=0.9")
                .addHeader("Authorization", "Bearer eyJ2ZXIiOiJ2MSIsImFsZyI6IkhTNTEyIn0.eyJzdWIiOiI4MzI5MzgiLCJpc3MiOiJzc28ucWllbWFuLmNvbSIsImV4cCI6MTY3ODk0ODAxOSwiaWF0IjoxNjc3NjUyMDE5LCJsb2dpbk1vZGUiOiJXRUNIQVQiLCJpc0FwcGxlVXNlck5vUGhvbmUiOmZhbHNlLCJqdGkiOiIzZGUzNmE1NS1hNjg1LTQ3OTYtYWI0YS1jYzQxOTQ3YjM1YzgifQ.utO2iMcvCNLzjx9ZnxqTjXcIErN_Ch4jWAVR8Y5OBl5ZegsyQ6zhth0uf9i35IGyO6Fx7fRFnx-XZBI_gpdBGw")
                .addHeader("Connection", "keep-alive")
                .addHeader("Referer", "https://qieman.com/orders?tab=%E7%9B%88%E7%B1%B3%E5%AE%9D%E8%B4%A6%E5%8D%95")
                .addHeader("Sec-Fetch-Dest", "empty")
                .addHeader("Sec-Fetch-Mode", "cors")
                .addHeader("Sec-Fetch-Site", "same-origin")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36")
                .addHeader("sec-ch-ua", "\".Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"103\", \"Chromium\";v=\"103\"")
                .addHeader("sec-ch-ua-mobile", "?0")
                .addHeader("sec-ch-ua-platform", "\"Windows\"")
                .addHeader("sensors-anonymous-id", "18560e1795292c-06ac16c16c16c18-c4e7526-2073600-18560e179531ed")
                .addHeader("x-request-id", "albus.C110769DD8B6E54AFF36")
                .addHeader("x-sign", "1677836411492A993BE489041D0E3298BC3472DC7C1C4")
                .addHeader("Cookie", "acw_tc=784e2c8c16778365500753690e6e2ad3272d7f69b9a86c978ba9cf4688f3ef")
                .build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
