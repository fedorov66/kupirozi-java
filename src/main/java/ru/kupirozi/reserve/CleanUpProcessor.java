package ru.kupirozi.reserve;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kupirozi.db.StorageManager;
import ru.kupirozi.utils.MiscUtils;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by fedorov on 28.02.2018.
 */
public class CleanUpProcessor extends Thread {

    Logger log = LoggerFactory.getLogger(CleanUpProcessor.class);

    private static StorageManager storage;

    public CleanUpProcessor(StorageManager storage) {
        setDaemon(true);
        this.storage = storage;
    }

    private void cleanUpExpiredRequests() {
        storage.execute((connection, pst, rs) -> {
            int timestamp = MiscUtils.time() - 25200;

            log.info("current time: " + timestamp);
            pst = connection.prepareStatement("SELECT count(*) as found FROM orders_reserved_items WHERE creation_date < ?");
            pst.setInt(1, timestamp);
            rs = pst.executeQuery();
            while (rs.next()) {
                log.info("found entries: " + rs.getInt("found"));
            }

            pst = connection.prepareStatement("DELETE FROM orders_reserved_items WHERE creation_date < ?");
            pst.setInt(1, timestamp);
            pst.executeUpdate();

            log.info("Remove all less: " + timestamp);
            return null;
        });
    }

    public void run() {
        try {
            System.out.println("Daemon thread is running");
            while (true) {
                cleanUpExpiredRequests();
                // sleep(1800000);
                sleep(60000);
            }
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            System.out.println("Daemon Thread exiting"); // never called
        }
    }

}
