package ru.kupirozi.db;

import ru.kupirozi.catalogue.Category;
import ru.kupirozi.catalogue.CategoryItems;
import ru.kupirozi.catalogue.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by fedorov on 01.02.2018.
 */
public class CategoryItemsHandler implements StorageHandler {

    private int catId;
    private boolean activeCategoryOnly;

    public CategoryItemsHandler(final int categoryId, final boolean activeCategory) {
        catId = categoryId;
        activeCategoryOnly = activeCategory;
    }

    @Override
    public Object handle(Connection connection, PreparedStatement pst, ResultSet rs) throws SQLException {

        long startTime = new Date().getTime();

        pst = connection.prepareStatement("SELECT * FROM categories WHERE id=? AND active=?");
        pst.setInt(1, catId);
        pst.setInt(2, activeCategoryOnly ? 1 : 0);
        rs = pst.executeQuery();

        CategoryItems categoryItems = new CategoryItems();
        while (rs.next()) {
            StorageHandlerHelper.populateCategory(categoryItems, rs, null);
        }

        pst = connection.prepareStatement("select it.id,it.category_id,it.name_ru,it.name_en,it.images,it.is_new,it.is_new_text,it.price,it.quantity,IFNULL(it.quantity - SUM(res.quantity),it.quantity) as quantityAvailable  from items as it LEFT JOIN orders_reserved_items as res ON it.id = res.item_id WHERE it.category_id=? GROUP BY it.id;");
        pst.setInt(1, catId);
        rs = pst.executeQuery();

        System.out.println(CategoryItemsHandler.class + " query complete in: " + (new Date().getTime() - startTime) + " ms");

        while (rs.next()) {
            categoryItems.getItems().add(StorageHandlerHelper.populateItem(new Item(), rs, null));
        }

        return categoryItems;
    }
}
