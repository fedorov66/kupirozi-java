package ru.kupirozi.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kupirozi.reserve.ReserveRequest;
import ru.kupirozi.utils.MiscUtils;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by fedorov on 02.02.2018.
 */
public class ReserveItemHandler implements StorageHandler {

    Logger log = LoggerFactory.getLogger(ReserveItemHandler.class);
    private ReserveRequest request;

    public ReserveItemHandler(final ReserveRequest request) {
        this.request = request;
    }

    @Override
    public Object handle(Connection connection, PreparedStatement pst, ResultSet rs) throws SQLException {
        pst = connection.prepareStatement("SELECT count(*) as QUANTITY FROM orders_reserved_items WHERE session_id=? AND item_id=?");
        pst.setString(1, request.getSessionId());
        pst.setInt(2, request.getItemId());

        rs = pst.executeQuery();

        int reservedQuantity = 0;
        while (rs.next()) {
            reservedQuantity = rs.getInt("QUANTITY");
        }

        log.info("Reserved items:" + reservedQuantity);

        pst.clearParameters();
        int timestamp = MiscUtils.time();

        boolean success = true;
        PreparedStatement updatePst = null;

        try {

            connection.setAutoCommit(false);

            if (reservedQuantity == 0) {
                log.info(String.format("Item item %d on session %s", request.getItemId(), request.getSessionId()));
                pst = connection.prepareStatement("INSERT INTO orders_reserved_items (session_id, item_id, quantity, creation_date) VALUES (?, ?, ?, ?)");
                pst.setString(1, request.getSessionId());
                pst.setInt(2, request.getItemId());
                pst.setInt(3, request.getQuantity());
                pst.setInt(4, timestamp);
            } else {
                if (request.getQuantity() == 0) {
                    pst = connection.prepareStatement(" DELETE FROM orders_reserved_items WHERE session_id=? AND item_id=?");
                    pst.setString(1, request.getSessionId());
                    pst.setInt(2, request.getItemId());
                } else {
                    log.info(String.format("Update item %d on session %s", request.getItemId(), request.getSessionId()));
                    pst = connection.prepareStatement("UPDATE orders_reserved_items SET quantity=?,creation_date=? WHERE session_id=? AND item_id=?");
                    pst.setInt(1, request.getQuantity() + reservedQuantity);
                    pst.setInt(2, timestamp);
                    pst.setString(3, request.getSessionId());
                    pst.setInt(4, request.getItemId());
                }
            }
            pst.executeUpdate();

            updatePst = connection.prepareStatement("UPDATE orders_reserved_items SET creation_date=? WHERE session_id=?");
            updatePst.setInt(1, timestamp);
            updatePst.setString(2, request.getSessionId());
            updatePst.executeUpdate();

            connection.commit();
            log.info("commit success");
        } catch (SQLException e) {
            log.error(e.getMessage());
            connection.rollback();
        } finally {

            if (updatePst != null) {
                updatePst.close();
            }
        }
        return true;
    }
}
