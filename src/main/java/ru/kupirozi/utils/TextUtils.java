package ru.kupirozi.utils;

import org.jsoup.Jsoup;

/**
 * Created by fedorov on 05.02.2018.
 */
public class TextUtils {

    public static boolean isBlank(String str) {
        int strLen;
        if(str != null && (strLen = str.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if(!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    public static boolean isBlank(final String str, final boolean trim) {
        return (str == null) || (trim ? isBlank(str) : str.length() == 0);
    }

    public static String html2text(String html) {
        return Jsoup.parse(html).text();
    }

}
