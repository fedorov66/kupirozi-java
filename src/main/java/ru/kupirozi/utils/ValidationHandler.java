package ru.kupirozi.utils;

import com.google.gson.Gson;
import spark.Request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fedorov on 05.02.2018.
 */
public class ValidationHandler {

    public static String handle(Request request, Map<String, ValidationTypes> params) {
        Map<String, String> validation = new HashMap<String, String>();

        // validate all params
        if (params == null || params.isEmpty()) {
            for (String key : request.params().keySet()) {
                if (TextUtils.isBlank(request.params(key), true)) {
                    validation.put(key, String.format("Необходимо заполнить поле {%s}", key));
                }
            }
        } else {
            for (String key : params.keySet()) {
                if (!request.params().containsKey(key)) {
                    validation.put(key, String.format("Не задан обязательный параметр {%s}", key));
                } else if (TextUtils.isBlank(request.params(key), true)) {
                    validation.put(key, String.format("Необходимо заполнить поле {%s}", key));
                } else {
                    switch (params.get(key)) {
                        case INTEGER:
                            try {
                                Integer.valueOf(request.params(key));
                            } catch (Exception e) {
                                validation.put(key, String.format("Не корретный параметр {%s}, ожидается число", key));
                            }
                            break;
                        default:
                            validation.put(key, String.format("Не задан валидационный обработчик обработчик {%s} для поля {%s}", params.get(key), key));
                    }
                }
            }
        }
        return validation.isEmpty() ? null : new Gson().toJson(validation);
    }

}
