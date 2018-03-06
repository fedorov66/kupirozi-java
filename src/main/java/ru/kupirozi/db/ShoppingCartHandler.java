package ru.kupirozi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by fedorov on 05.02.2018.
 */
public class ShoppingCartHandler implements StorageHandler {

    private String sessionId;

    public ShoppingCartHandler(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public Object handle(Connection connection, PreparedStatement pst, ResultSet rs) throws SQLException {
        pst = connection.prepareStatement("SELECT res.quantity as quantity,it.id, it.name_ru,it.price FROM orders_reserved_items as res INNER JOIN items as it WHERE res.session_id=? AND res.item_id=it.id GROUP BY res.item_id ORDER BY it.name_ru;");
        pst.setString(1, sessionId);
        rs = pst.executeQuery();

        return StorageHandlerHelper.populateShoppingCart(rs);
    }
}
