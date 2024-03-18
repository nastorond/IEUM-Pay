package com.ieum.common.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    public String getAuthentificationCode(String phoneNumber) {
        // 영어 대소문자와 숫자를 포함한 16자리 난수 생성
        String code = "ieum-pay Authentification Code:" + RandomStringUtils.randomAlphanumeric(32);

        // key phoneNumber
        // value code
        // redis에 저장

        return code;
    }
}
