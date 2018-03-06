package ru.kupirozi.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by fedorov on 01.02.2018.
 */
public class Config {
    private static Logger log = LoggerFactory.getLogger(Config.class);
    private final DbConfig db = new DbConfig();
    private final AppConfig app = new AppConfig();
    private final MailConfig mail = new MailConfig();

    public Config(String[] args) {
        log.info("args: {}", Arrays.asList(args));
        log.info("properties: {}", System.getProperties());
        Properties properties = new Properties();
        try (InputStream input = getConfig()) {
            properties.load(input);
        } catch (IOException e) {
            log.error("load config", e);
        }

        // database
        db.url = properties.getProperty("db.url");
        db.username = properties.getProperty("db.username");
        db.password = properties.getProperty("db.password");

        // mail
        mail.host = properties.getProperty("mail.host");
        mail.port = Integer.parseInt(properties.getProperty("mail.port"));
        mail.username = properties.getProperty("mail.username");
        mail.password = properties.getProperty("mail.password");

        // app
        app.port = Integer.parseInt(properties.getProperty("app.port"));
    }

    private InputStream getConfig() throws FileNotFoundException {
        InputStream input;
        String configFileName = System.getProperty("config");
        if (configFileName != null) {
            input = new FileInputStream(configFileName);
        } else {
            input = getClass().getResourceAsStream("/config/app.properties");
        }
        return input;
    }

    public DbConfig getDbCfg() {
        return db;
    }

    public AppConfig getAppCfg() {
        return app;
    }

    public MailConfig getMailConfig() { return mail; }
}
