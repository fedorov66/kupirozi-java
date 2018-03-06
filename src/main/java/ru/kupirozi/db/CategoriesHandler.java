package ru.kupirozi.db;

import ru.kupirozi.catalogue.Category;
import ru.kupirozi.catalogue.Image;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fedorov on 01.02.2018.
 */
public class CategoriesHandler implements StorageHandler {

    private boolean active = false;

    public CategoriesHandler() {
    }

    public CategoriesHandler(final boolean activeOnly) {
        this.active = activeOnly;
    }

    @Override
    public Object handle(Connection connection, PreparedStatement pst, ResultSet rs) throws SQLException {

        long startTime = new Date().getTime();

        pst = connection.prepareStatement("SELECT * FROM categories WHERE active=?");
        pst.setInt(1, (active ? 1 : 0));
        rs = pst.executeQuery();

        System.out.println(CategoriesHandler.class + " query complete in: " + (new Date().getTime() - startTime) + " ms");

        List<Category> items = new ArrayList<Category>();

        while (rs.next()) {
            items.add(StorageHandlerHelper.populateCategory(new Category(), rs, null));
        }

        return items;
    }
}
