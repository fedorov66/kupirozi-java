package ru.kupirozi.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kupirozi.config.DbConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by fedorov on 31.01.2018.
 */
public class StorageManager  {

    private static volatile StorageManager instance;

    private HikariDataSource ds = null;

    public static StorageManager getInstance() {
        StorageManager localInstance = instance;
        if (localInstance == null) {
            synchronized (StorageManager.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new StorageManager();
                }
            }
        }
        return localInstance;
    }

    public void configure(final DbConfig dbConfig) {
        Properties config = new Properties();
        config.put("jdbcUrl", dbConfig.getUrl());
        config.put("username", dbConfig.getUsername());
        config.put("password", dbConfig.getPassword());
        config.put("maximumPoolSize", 10);
        config.put("idleTimeout", 30000);
        HikariConfig cfg = new HikariConfig(config);
        ds = new HikariDataSource(cfg);
    }

    public Object execute(final StorageHandler handler) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        Object result = null;

        try {
            con = ds.getConnection();
            result = handler.handle(con, pst, rs);
        } catch (SQLException ex) {

            Logger lgr = LoggerFactory.getLogger(StorageManager.class);
            lgr.error(ex.getMessage(), ex);

        } finally {

            try {

                if (rs != null) {
                    rs.close();
                }

                if (pst != null) {
                    pst.close();
                }

                if (con != null) {
                    con.close();
                }

                //ds.close();

            } catch (SQLException ex) {

                Logger lgr = LoggerFactory.getLogger(StorageManager.class);
                lgr.warn(ex.getMessage(), ex);
            }

            return result;
        }
    }


}
