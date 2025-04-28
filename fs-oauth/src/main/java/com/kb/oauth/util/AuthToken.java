package com.kb.oauth.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @author syg
 */
@Data
public class AuthToken implements Serializable {
    /**身份令牌信息*/
    private String accessToken;
    /**刷新token*/
    private String refreshToken;
    /**jwt短令牌*/
    private String jwtToken;
}
