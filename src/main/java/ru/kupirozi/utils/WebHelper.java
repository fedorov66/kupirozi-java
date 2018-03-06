package ru.kupirozi.utils;

import spark.Request;

/**
 * Created by fedorov on 05.02.2018.
 */
public class WebHelper {

    public static String USER_ID_COOKIE = "__user_id";
    public static String AUTH_COOKIE = "__user_auth_token";

    public static String getUserIdFromCookie(final Request request) {
        return getCookieValue(request, USER_ID_COOKIE);
    }

    public static String getAuthCookie(final Request request) {
        return getCookieValue(request, AUTH_COOKIE);
    }

    private static String getCookieValue(final Request request, final String key) {
        String value = request.cookie(key);
        if (value == null || "".equals(value)) {
            value = request.attribute(key);
        }
        return value;
    }

}
