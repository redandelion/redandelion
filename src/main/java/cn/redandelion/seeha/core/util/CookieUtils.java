package cn.redandelion.seeha.core.util;

import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import org.apache.poi.util.ArrayUtil;
import org.apache.poi.util.StringUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;

public class CookieUtils {
    public static final int COOKIE_MAX_AGE = 7 * 24 * 3600;
    public static final int COOKIE_HALF_HOUR = 30;
    /**
     * 根据Cookie名称得到Cookie对象，不存在该对象则返回Null
     *
     * @param request
     * @param name
     * @return
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies.length==0) {
            return null;
        }
        Cookie cookie = null;
        for (Cookie c : cookies) {
            if (name.equals(c.getName())) {
                cookie = c;
                break;
            }
        }
        return cookie;
    }
    /**

     * 根据Cookie名称直接得到Cookie值

     *

     * @param request

     * @param name

     * @return

     */

    public static String getCookieValue(HttpServletRequest request, String name) {

        Cookie cookie = getCookie(request, name);

        if(cookie != null){

            return cookie.getValue();

        }

        return null;

    }
    /**

     * 移除cookie

     * @param request

     * @param response

     * @param name 这个是名称，不是值

     */
    public static void removeCookie(HttpServletRequest request,
                                    HttpServletResponse response, String name) {
        if (null == name) {
            return;
        }
        Cookie cookie = getCookie(request, name);
        if(null != cookie){
            cookie.setPath("/");
            cookie.setValue("");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }
    /**

     * 添加一条新的Cookie，可以指定过期时间(单位：秒)

     *

     * @param response

     * @param name

     * @param value

     * @param maxValue

     */

    public static void setCookie(HttpServletResponse response, String name,
                                 String value, int maxValue) {
        if ("".equals(name)||name==null) {
            return;
        }
        if (null == value) {
            value = "";
        }
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (maxValue != 0) {
            cookie.setMaxAge(maxValue);
        } else {
            cookie.setMaxAge(COOKIE_HALF_HOUR);
        }
        response.addCookie(cookie);
        try {
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * 添加一条新的Cookie，默认30分钟过期时间
     *
     * @param response

     * @param name

     * @param value

     */

    public static void setCookie(HttpServletResponse response, String name,
                                 String value) {
        setCookie(response, name, value, COOKIE_HALF_HOUR);

    }


    public static void setRequestFromCookie(IRequest iRequest,HttpServletResponse response) {
        setCookie(response, "userId", iRequest.getUserId().toString());
        setCookie(response, "roleId", iRequest.getRoleId().toString());
        setCookie(response, "allRoleId", iRequest.getAllRoleId().toString());
    }
    public static void getRequestFromCookie(IRequest iRequest,HttpServletRequest request) {
        if ("".equals(getCookieValue(request,"userId"))){
            iRequest.setUserId(-1L);
        }else {
            iRequest.setUserId(Long.valueOf(getCookieValue(request,"userId")));
        }
        if ("".equals(getCookieValue(request,"roleId"))){
            iRequest.setRoleId(-1L);
        }else {
            iRequest.setRoleId(Long.valueOf(getCookieValue(request,"roleId")));
        }
        if ("".equals(getCookieValue(request,"allRoleId"))){
            iRequest.setAllRoleId(null);
        }else {
            String ids = getCookieValue(request,"allRoleId").substring(1,getCookieValue(request,"allRoleId").length()-1);
            String[] splitIds = ids.split(",");
            Long[] id = new Long[splitIds.length];
            for (int i = 0 ; i<splitIds.length;i++){
                id[i] = Long.parseLong(splitIds[i]);
            }
            iRequest.setAllRoleId(id);
        }
    }
}
