package ru.kupirozi.routes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kupirozi.db.ShoppingCartHandler;
import ru.kupirozi.db.StorageManager;
import ru.kupirozi.reserve.RequestsProcessor;
import ru.kupirozi.reserve.ReserveRequest;
import ru.kupirozi.utils.WebHelper;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by fedorov on 05.02.2018.
 */
public class ShoppingCartRoutes {

    private static Logger log = LoggerFactory.getLogger(ShoppingCartRoutes.class);

    public static class Add extends BaseRoute {

        public Add(final RequestsProcessor processor, final StorageManager storage) {
            super(processor, storage);
        }

        @Override
        public Object handle(Request request, Response response) throws Exception {
            JsonParser jsonParser = new JsonParser();
            JsonObject requestJson = (JsonObject)jsonParser.parse(request.body());

            ReserveRequest reserveRequest = new ReserveRequest();
            reserveRequest.setSessionId(WebHelper.getUserIdFromCookie(request));
            reserveRequest.setItemId(Integer.valueOf(requestJson.get("item_id").getAsInt()));
            reserveRequest.setQuantity(Integer.valueOf(requestJson.get("quantity").getAsInt()));

            log.info("request added:" + reserveRequest.getUid());

            synchronized(reserveRequest) {
                getProcessor().addRequets(reserveRequest);
                while (!getProcessor().isReady(reserveRequest.getUid())){
                    reserveRequest.wait();
                }
            }

            reserveRequest.setShoppingCart((ru.kupirozi.catalogue.ShoppingCart)getStorage().execute(new ShoppingCartHandler(reserveRequest.getSessionId())));

            log.info("request finished:" + reserveRequest.getUid() + " => " + reserveRequest.getStatus().name());
            getProcessor().getProcessed().remove(reserveRequest.getUid());

            return toJSON(reserveRequest);
        }
    }

    public static class Items extends BaseRoute {

        public Items(final StorageManager storage) {
            super(storage);
        }

        @Override
        public Object handle(Request request, Response response) throws Exception {
            return toJSON((ru.kupirozi.catalogue.ShoppingCart)getStorage().execute(new ShoppingCartHandler(WebHelper.getUserIdFromCookie(request))));
        }
    }

}
