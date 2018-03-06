package ru.kupirozi.routes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.kupirozi.db.StorageManager;
import ru.kupirozi.reserve.RequestsProcessor;
import ru.kupirozi.utils.ValidationTypes;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fedorov on 16.02.2018.
 */
public class BaseRoute implements Route {

    private Object defaultObj;
    private RequestsProcessor processor;
    private StorageManager storage;
    private final Map<String, ValidationTypes> validationMap = new HashMap<>();

    public BaseRoute(final RequestsProcessor processor, final StorageManager storage) {
        this.processor = processor;
        this.storage = storage;
    }

    public BaseRoute(final StorageManager storage) {
        this.storage = storage;
    }

    public BaseRoute(final Object obj) {
        defaultObj = obj;
    }

    public BaseRoute() {}

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if (!validationMap.isEmpty()) {

        }
        return gson.toJson(defaultObj);
    }

    public RequestsProcessor getProcessor() {
        return processor;
    }

    public StorageManager getStorage() {
        return storage;
    }

    public Map<String, ValidationTypes> getValidationMap() {
        return validationMap;
    }

    public String toJSON(final Object object) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(object);
    }

}
