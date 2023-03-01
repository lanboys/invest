package com.bing.lan.invest.utils;

import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by lanbing at 2023/2/21 9:01
 */

@Slf4j
public class UrlUtil {

    @Data
    public static class UrlEntity {

        /**
         * 基础url
         */
        public String url = "";
        /**
         * url参数
         */
        public Map<String, String> params = new HashMap<>();

        public String getParam(String name) {
            return params.get(name);
        }
    }

    /**
     * 解析url
     */
    public static UrlEntity parse(String url) {
        log.debug("parse url: {}", url);
        UrlEntity entity = new UrlEntity();
        if (ObjectUtils.isEmpty(url)) {
            return entity;
        }
        String[] urlParts = url.split("\\?");
        entity.url = urlParts[0];
        // 没有参数
        if (urlParts.length == 1) {
            return entity;
        }
        // 有参数
        String[] params = urlParts[1].split("&");
        for (String param : params) {
            String[] keyValue = param.split("=");
            entity.params.put(keyValue[0], keyValue[1]);
        }
        return entity;
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        UrlEntity entity = parse(null);
        System.out.println(entity.url + "\n" + entity.params);
        entity = parse("http://www.123.com");
        System.out.println(entity.url + "\n" + entity.params);
        entity = parse("http://www.123.com?id=1");
        System.out.println(entity.url + "\n" + entity.params);
        entity = parse("http://www.123.com?id=1&name=小明");
        System.out.println(entity.url + "\n" + entity.params);
    }
}


