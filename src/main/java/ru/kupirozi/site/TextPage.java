package ru.kupirozi.site;

/**
 * Created by fedorov on 16.02.2018.
 */
public class TextPage {

    private int id;
    private int parent_id = -1;
    private int weight = 0;
    private String uri;
    private String title;
    private String category_name;
    private String text;
    private boolean is_category = false;
    private boolean is_hidden = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parent_id;
    }

    public void setParentId(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryName() {
        return category_name;
    }

    public void setCategoryName(String category_name) {
        this.category_name = category_name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCategory() {
        return is_category;
    }

    public void setIsCategory(boolean is_category) {
        this.is_category = is_category;
    }

    public boolean isHidden() {
        return is_hidden;
    }

    public void setIsHidden(boolean is_hidden) {
        this.is_hidden = is_hidden;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
