package com.kb.common.utils;

import cn.hutool.crypto.digest.MD5;

/**
 * @author syg
 * @version 1.0
 */
public class Md5Util {
    /**
     * 生成16位摘要
     * @param data
     * @return
     */
    public static String encode(String data){
        return MD5.create().digestHex(data);
    }

}
