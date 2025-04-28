package com.kb.oauth.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author syg
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialInfo {

    private Integer id;

    private String socialId;

    private Integer userId;
}
