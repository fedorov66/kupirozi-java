package ru.kupirozi.catalogue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fedorov on 01.02.2018.
 */
public class Item {

    private int id;
    private int categoryId;
    private int quantity = 0;
    private Integer quantityAvailable = null;
    private String nameRu;
    private String nameEn;
    private final List<Image> images = new ArrayList<Image>();
    private BigDecimal price;
    private boolean isNew;
    private String isNewText;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantityAvailable() {
        return quantityAvailable == null ? quantity : quantityAvailable;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public List<Image> getImages() {
        return images;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(int aNew) {
        isNew = aNew == 1;
    }

    public String getIsNewText() {
        return isNewText;
    }

    public void setIsNewText(String isNewText) {
        this.isNewText = isNewText;
    }
}
