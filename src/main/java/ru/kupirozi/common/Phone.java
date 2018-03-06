package ru.kupirozi.common;

import ru.kupirozi.utils.TextUtils;

/**
 * Created by fedorov on 28.02.2018.
 */
public class Phone {

    private String country;
    private String code;
    private String number;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFullNumber() {
        StringBuilder fullNumber = new StringBuilder();
        if (!TextUtils.isBlank(country)) {
            fullNumber.append(country);
        } else {
            fullNumber.append("7");
        }
        if (!TextUtils.isBlank(code)) {
            fullNumber.append("(");
            fullNumber.append(code);
            fullNumber.append(")");
        }
        if (!TextUtils.isBlank(number)) {
            fullNumber.append(number);
        }
        return fullNumber.toString();
    }

}
