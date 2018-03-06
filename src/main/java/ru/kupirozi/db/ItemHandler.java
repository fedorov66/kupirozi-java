package ru.kupirozi.db;

import ru.kupirozi.catalogue.ItemFull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by fedorov on 01.02.2018.
 */
public class ItemHandler implements StorageHandler {

    private Integer id;

    public ItemHandler(final Integer id) {
        this.id = id;
    }

    @Override
    public Object handle(Connection connection, PreparedStatement pst, ResultSet rs) throws SQLException {

        if (id == null) {
            return null;
        }

        long startTime = new Date().getTime();

        //pst = connection.prepareStatement("SELECT * FROM items where id=? limit 1");
        pst = connection.prepareStatement("SELECT it.*,IFNULL((it.quantity - SUM(itms_res.quantity)),it.quantity) as quantityAvailable FROM items as it INNER JOIN orders_reserved_items as itms_res WHERE it.id=? && itms_res.item_id=?;");
        pst.setInt(1, id);
        pst.setInt(2, id);
        rs = pst.executeQuery();

        System.out.println(ItemHandler.class + " query complete in: " + (new Date().getTime() - startTime) + " ms");

        ItemFull item = new ItemFull();
        while (rs.next()) {
            StorageHandlerHelper.populateItemFull(item, rs, null);
        }
        return item;
    }
}
