package ru.kupirozi.routes;

import ru.kupirozi.catalogue.CategoryItems;
import ru.kupirozi.catalogue.ItemFull;
import ru.kupirozi.db.CategoriesHandler;
import ru.kupirozi.db.CategoryItemsHandler;
import ru.kupirozi.db.ItemHandler;
import ru.kupirozi.db.StorageManager;
import ru.kupirozi.utils.ValidationHandler;
import ru.kupirozi.utils.ValidationTypes;
import spark.Request;
import spark.Response;
import java.util.List;

/**
 * Created by fedorov on 01.03.2018.
 */
public class CatalogueRoutes {


    public static class Category extends BaseRoute {

        public Category(final StorageManager storage) {
            super(storage);
        }

        @Override
        public Object handle(Request request, Response response) throws Exception {
            getValidationMap().put(":id", ValidationTypes.INTEGER);
            String validation = ValidationHandler.handle(request, getValidationMap());
            if (validation != null) {
                return validation;
            }
            return toJSON((CategoryItems)getStorage().execute(new CategoryItemsHandler(Integer.valueOf(request.params(":id")), true)));
        }

    }

    public static class Categories extends BaseRoute {

        public Categories(final StorageManager storage) {
            super(storage);
        }

        @Override
        public Object handle(Request request, Response response) throws Exception {
            return toJSON((List<ru.kupirozi.catalogue.Category>)getStorage().execute(new CategoriesHandler(true)));
        }
    }

    public static class Item extends BaseRoute {

        public Item(final StorageManager storage) {
            super(storage);
        }

        @Override
        public Object handle(Request request, Response response) throws Exception {
            getValidationMap().put(":id", ValidationTypes.INTEGER);
            String validation = ValidationHandler.handle(request, getValidationMap());
            if (validation != null) {
                return validation;
            }
            return toJSON((ItemFull)getStorage().execute(new ItemHandler(Integer.valueOf(request.params(":id")))));
        }
    }



}
