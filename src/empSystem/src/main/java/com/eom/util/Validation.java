package com.eom.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    // Employee 클래스: Setter/Getter
    // Validation 클래스: 입력 유효성 검사(Employee 객체 생성 전)
    public static boolean checkPhoneNumFormat(String phoneNum) {
        Pattern pattern = Pattern.compile("^[0-9]{2,3}-[0,9]{3,4}-[0,9]{4}$");
        Matcher matcher = pattern.matcher(phoneNum);

        return matcher.find();
    }

    public static boolean checkEmailFormat(String email) {
        Pattern pattern = Pattern.compile("(^[0-9a-zA-Z_-]+@[0-9a-zA-Z]+[.][a-zA-Z]{2,4}$)");
        Matcher matcher = pattern.matcher(email);

        return matcher.find();
    }
}
