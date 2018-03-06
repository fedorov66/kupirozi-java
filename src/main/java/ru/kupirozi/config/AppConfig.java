package ru.kupirozi.config;

/**
 * Created by fedorov on 01.02.2018.
 */
public class AppConfig {
    int port;
    String webApp;
    boolean webAppInJar;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getWebApp() {
        return webApp;
    }

    public boolean isWebAppInJar() {
        return webAppInJar;
    }
}
