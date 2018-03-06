package ru.kupirozi.routes;

import com.google.gson.*;
import ru.kupirozi.catalogue.order.Order;
import ru.kupirozi.catalogue.order.OrderItem;
import ru.kupirozi.db.StorageManager;
import ru.kupirozi.reserve.RequestsProcessor;
import ru.kupirozi.utils.ValidationHandler;
import ru.kupirozi.utils.ValidationTypes;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by fedorov on 16.02.2018.
 */
public class OrderRoute extends BaseRoute {

    public OrderRoute(final StorageManager storage) {
        super(storage);
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Order order = new Order();
        JsonParser jsonParser = new JsonParser();

        JsonObject requestJson = (JsonObject)jsonParser.parse(request.body());

        for (Iterator<JsonElement> it = requestJson.getAsJsonArray("items").iterator(); it.hasNext();) {
            JsonObject item = it.next().getAsJsonObject();
            OrderItem orderItem = new OrderItem();
            orderItem.setId(item.get("id").getAsInt());
            orderItem.setConfirmed(false);
        }

        return new Gson().toJson(order);
    }
}
