package ru.kupirozi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kupirozi.db.*;
import ru.kupirozi.config.AppConfig;
import ru.kupirozi.config.Config;
import ru.kupirozi.reserve.*;
import ru.kupirozi.routes.*;
import ru.kupirozi.utils.WebHelper;
import spark.*;

import javax.servlet.http.Cookie;
import java.io.IOException;
import java.util.*;

import static spark.Spark.*;

/**
 * Created by fedorov on 17.01.2018.
 */
public class WebServer {

    private static Logger log = LoggerFactory.getLogger(WebServer.class);

    public static void main(String[] args)  throws IOException {

        System.setProperty("org.jboss.logging.provider", "slf4j");

        log.info("start app init");
        log.info("read config");
        Config config = new Config(args);

        final AppConfig appCfg = config.getAppCfg();

        log.info("init StorageManager");

        StorageManager storage = StorageManager.getInstance();
        storage.configure(config.getDbCfg());

        port(appCfg.getPort());

       log.info("init RequestsProcessor");

       final RequestsProcessor processor = new RequestsProcessor(storage);
       final CleanUpProcessor cleanUpProcessor = new CleanUpProcessor(storage);

        path("/api", new RouteGroup() {
            public void addRoutes() {

                before("/*", (request, response) -> {
                    // generate "USER SESSION ID"
                    String userId = request.cookie(WebHelper.USER_ID_COOKIE);
                    if (userId == null || "".equals(userId)) {
                        userId = UUID.randomUUID().toString();
                        Cookie cookie = new Cookie(WebHelper.USER_ID_COOKIE, userId);
                        cookie.setPath("/");
                        cookie.setMaxAge(604800);
                        response.raw().addCookie(cookie);
                        // pass cookie data to first request
                        request.attribute(WebHelper.USER_ID_COOKIE, userId);
                    }
                    response.header("Content-Type", "application/json;charset=utf-8");
                });

                get("/sys/info", new BaseRoute(processor.getQueue()));
                get("/page/:uri", new SiteRoutes.Page(storage));
                get("/navigation", new SiteRoutes.Menu());
                get("/categories", new CatalogueRoutes.Categories(storage));
                get("/category/:id", new CatalogueRoutes.Category(storage));
                get("/category/:catId/:id", new CatalogueRoutes.Item(storage));
                get("/shopping_cart", new ShoppingCartRoutes.Items(storage));
                post("/shopping_cart/add", new ShoppingCartRoutes.Add(processor, storage));
                put("/createOrder", new OrderRoute(storage));

            }
        });

        log.info("run cleanup daemon");
        cleanUpProcessor.start();

        log.info("run RequestsProcessor");
        processor.start();


    }

}
