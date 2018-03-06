package ru.kupirozi.catalogue;

import java.math.BigDecimal;

/**
 * Created by fedorov on 01.02.2018.
 */
public class ItemFull extends Item {

    private String author;
    private String color;
    private String height;
    private String width;
    private String size;
    private String petalsNum;
    private String stabMuchRosa;
    private String stabBlankPyat;
    private String bloom;
    private String aroma;
    private String description;
    private boolean isVesna;
    private boolean isOsen;
    private boolean isTest;
    private boolean direction;
    private String freeTextQuantity;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPetalsNum() {
        return petalsNum;
    }

    public void setPetalsNum(String petalsNum) {
        this.petalsNum = petalsNum;
    }

    public String getStabMuchRosa() {
        return stabMuchRosa;
    }

    public void setStabMuchRosa(String stabMuchRosa) {
        this.stabMuchRosa = stabMuchRosa;
    }

    public String getStabBlankPyat() {
        return stabBlankPyat;
    }

    public void setStabBlankPyat(String stabBlankPyat) {
        this.stabBlankPyat = stabBlankPyat;
    }

    public String getBloom() {
        return bloom;
    }

    public void setBloom(String bloom) {
        this.bloom = bloom;
    }

    public String getAroma() {
        return aroma;
    }

    public void setAroma(String aroma) {
        this.aroma = aroma;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isVesna() {
        return isVesna;
    }

    public void setVesna(boolean vesna) {
        isVesna = vesna;
    }

    public boolean isOsen() {
        return isOsen;
    }

    public void setOsen(boolean osen) {
        isOsen = osen;
    }

    public boolean isTest() {
        return isTest;
    }

    public void setTest(boolean test) {
        isTest = test;
    }

    public boolean isDirection() {
        return direction;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public String getFreeTextQuantity() {
        return freeTextQuantity;
    }

    public void setFreeTextQuantity(String freeTextQuantity) {
        this.freeTextQuantity = freeTextQuantity;
    }
}
