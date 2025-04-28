package com.kb.oauth.vo.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author syg
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterParam {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
