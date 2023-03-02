package com.bing.lan.invest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public String file(String accountCode) {
        return accountCode;
    }
}
