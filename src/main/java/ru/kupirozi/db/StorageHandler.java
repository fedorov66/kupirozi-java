package ru.kupirozi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by fedorov on 01.02.2018.
 */
public interface StorageHandler {

    Object handle(final Connection connection, final PreparedStatement pst, final ResultSet rs) throws SQLException;

}
