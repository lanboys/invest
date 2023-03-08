package com.bing.lan.invest.utils;

import org.springframework.util.ObjectUtils;

import java.math.*;

/**
 * Created by lanbing at 2023/3/8 14:31
 */

public class BigDecimalUtil {

    public static BigDecimal objToBigDecimal(Object obj) {
        if (ObjectUtils.isEmpty(obj)) {
            return BigDecimal.ZERO;
        }
        if (obj instanceof String) {
            return new BigDecimal((String) obj);
        }
        if (obj instanceof Integer) {
            return new BigDecimal((Integer) obj);
        }
        if (obj instanceof Long) {
            return new BigDecimal((Long) obj);
        }
        if (obj instanceof Double) {
            return BigDecimal.valueOf((Double) obj);
        }
        if (obj instanceof Float) {
            return BigDecimal.valueOf((Float) obj);
        }
        throw new RuntimeException("不支持的类型转换");
    }
}
