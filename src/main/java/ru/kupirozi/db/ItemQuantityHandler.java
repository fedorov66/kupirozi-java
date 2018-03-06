package ru.kupirozi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by fedorov on 01.02.2018.
 */
public class ItemQuantityHandler implements StorageHandler {

    private int id;

    public ItemQuantityHandler(final int id) {
        this.id = id;
    }

    @Override
    public Object handle(Connection connection, PreparedStatement pst, ResultSet rs) throws SQLException {

        long startTime = new Date().getTime();

        pst = connection.prepareStatement("SELECT itms.quantity, sum(itms_res.quantity) as reserved FROM `items` as itms INNER JOIN orders_reserved_items as itms_res WHERE itms.id=? && itms_res.item_id=? limit 1;");
        pst.setInt(1, id);
        pst.setInt(2, id);

        rs = pst.executeQuery();

        System.out.println(ItemQuantityHandler.class + " query complete in: " + (new Date().getTime() - startTime) + " ms");

        int quantity = 0;

        while (rs.next()) {
            quantity = rs.getInt("quantity") - rs.getInt("reserved");
        }

        return quantity;
    }
}
