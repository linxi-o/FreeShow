package com.kb.oauth.util;

/**
 * @author syg
 * 生成六位数的验证码
 */
public class SmsCodeBuildUtils {
    /**
     * 生成验证码
     * @return
     */
    public static String getSixCode(){
        String code = String.valueOf((int)((Math.random()*9+1) * Math.pow(10,5)));
        return code;
    }
}
