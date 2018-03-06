package ru.kupirozi.catalogue;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by fedorov on 29.01.2018.
 */
public class Category {

    private int id;
    private int weight;
    private String uri;
    private String name;
    private String description;
    private Boolean active;
    private Set<Image> images = new HashSet<Image>();

    public Category(){
    }


    public String getUri() {
        return this.uri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return Boolean.TRUE.equals(active);
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Set<Image> getImages() {
        return images;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
