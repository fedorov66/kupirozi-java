package ru.kupirozi.site;

/**
 * Created by fedorov on 29.01.2018.
 */
public class NavItem {

    private String name;
    private String uri;
    private Boolean important;

    public NavItem(final String name, final String uri, final Boolean important) {
        this.name = name;
        this.uri = uri;
        this.important = Boolean.TRUE.equals(important);
    }

    public String getName() {
        return name;
    }

    public String getUri() {
        return uri;
    }

    public Boolean getImportant() {
        return important;
    }
}
