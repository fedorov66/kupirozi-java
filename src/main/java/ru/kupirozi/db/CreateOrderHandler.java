package ru.kupirozi.db;

import ru.kupirozi.catalogue.order.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by fedorov on 16.02.2018.
 */
public class CreateOrderHandler implements StorageHandler {

    private Order order;

    public CreateOrderHandler(Order order) {
        this.order = order;
    }

    @Override
    public Object handle(Connection connection, PreparedStatement pst, ResultSet rs) throws SQLException {




        return order;
    }
}
