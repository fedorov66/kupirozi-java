package ru.kupirozi.routes;

import ru.kupirozi.db.StorageManager;
import ru.kupirozi.db.TextPageHandler;
import ru.kupirozi.site.NavItem;
import ru.kupirozi.site.TextPage;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fedorov on 01.03.2018.
 */
public class SiteRoutes {

    public static class Menu extends BaseRoute {

        private static final List<NavItem> menu = new ArrayList<NavItem>();

        {
            menu.add(new NavItem("Главная страница", "/", false));
            menu.add(new NavItem("Каталог на весну 2018 года", "/catalogue/", true));
            menu.add(new NavItem("Как оформить заказ", "/page/how-to-order/", false));
            menu.add(new NavItem("Наши контакты", "/page/contacts/", false));
            menu.add(new NavItem("Фото", "/page/photos/", false));
            menu.add(new NavItem("Статьи", "/page/articles/", false));
            menu.add(new NavItem("Работа с претензиями", "/page/claims/", false));
        }

        @Override
        public Object handle(Request request, Response response) throws Exception {
            return toJSON(menu);
        }
    }

    public static class Page extends BaseRoute {

        public Page(StorageManager storage) {
            super(storage);
        }

        @Override
        public Object handle(Request request, Response response) throws Exception {
            return toJSON((TextPage)getStorage().execute(new TextPageHandler(request.params(":uri"))));
        }
    }

}
