package ru.kupirozi.catalogue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fedorov on 12.02.2018.
 */
public class ShoppingCart {

    private final List<ReservedItem> items = new ArrayList<>();

    public List<ReservedItem> getItems() {
        return items;
    }
}
