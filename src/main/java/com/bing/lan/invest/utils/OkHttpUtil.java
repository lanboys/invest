package com.bing.lan.invest.utils;

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

    public static String request(Integer pageSize, String beginTime, String endTime, String filterTurnoverIds) {
        String url = "https://qieman.com/pmdj/v2/wallet/turnovers?pageSize=" + pageSize + "&beginTime=" + beginTime + "&endTime" +
                "=" + endTime + "&paymentMethodId=92dqwqxl3g38&filterTurnoverIds=" + filterTurnoverIds;

        MediaType mediaType = MediaType.parse("text/plain");
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
