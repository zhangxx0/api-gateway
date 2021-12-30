package com.xinxin.apigateway.util;


import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 获取Gateway请求中request中的cookie处理工具类
 *
 * @author zhang.xx
 * @date 2021年12月30日
 */
public class CookieUtil {
    /*public static void set(HttpServletResponse response,
                           String name,
                           String value,
                           int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public static Cookie get(HttpServletRequest request,
                             String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie: cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }

        return null;
    }*/

    public static String getHttpCookie(ServerHttpRequest request,
                                         String name) {
        Map<String, String> map = new HashMap<>();
        Set<Map.Entry<String, List<HttpCookie>>> cookies = request.getCookies().entrySet();
        for (Map.Entry<String, List<HttpCookie>> entry : cookies) {
            map.put(entry.getKey(), entry.getValue().get(0).getValue());
        }
        return map.get(name);
    }
}
