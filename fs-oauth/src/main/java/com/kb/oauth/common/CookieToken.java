package com.kb.oauth.common;

import com.kb.oauth.util.CookieUtil;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author syg
 */
@Component
public class CookieToken {

    /**
     * 将令牌存储到cookie
     * @param token
     */
    public void saveCookie(HttpServletResponse response, String token, String domain, int maxAge){
        //httpOnly：设置为只读模式
        CookieUtil.addCookie(response,domain,"/","uid",token,maxAge,false);
    }
    /**
     * 从cookie删除token
     */
    public void clearCookie(HttpServletResponse response,String token,String domain){
        CookieUtil.addCookie(response,domain,"/","uid",token,0,false);

    }
    /**
     * 取出cookie中的身份令牌
     * @return
     */
    public String getTokenFormCookie(HttpServletRequest request){
        Map<String, String> map = CookieUtil.readCookie(request, "uid");
        if(map!=null && map.get("uid")!=null){
            String uid = map.get("uid");
            return uid;
        }
        return null;
    }

}
