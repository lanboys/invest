package com.bing.lan.invest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 投资基金详情 前端控制器
 * </p>
 *
 * @author oopcoder
 * @since 2023-03-02
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test(String accountCode) {
        return accountCode;
    }
}
