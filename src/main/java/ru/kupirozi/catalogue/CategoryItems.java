package ru.kupirozi.catalogue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fedorov on 01.02.2018.
 */
public class CategoryItems extends Category {

    private final List<Item> items = new ArrayList<Item>();

    public List<Item> getItems() {
        return items;
    }

}
