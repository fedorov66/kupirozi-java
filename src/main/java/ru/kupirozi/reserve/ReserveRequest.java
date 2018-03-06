package ru.kupirozi.reserve;

import ru.kupirozi.catalogue.ShoppingCart;
import ru.kupirozi.utils.MiscUtils;

import java.util.Date;
import java.util.UUID;

/**
 * Created by fedorov on 18.01.2018.
 */
public class ReserveRequest {

    private String sessionId;
    private String uid;
    private int itemId;
    private int quantity;
    private int available;
    private int availableInDb;
    private RequestStatus status;
    private long timestamp;
    private ru.kupirozi.catalogue.ShoppingCart shoppingCart;

    public ReserveRequest() {
        status = RequestStatus.NEW;
        timestamp = MiscUtils.time();
        uid = UUID.randomUUID().toString();
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int id) {
        itemId = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getAvailableInDb() {
        return availableInDb;
    }

    public void setAvailableInDb(int availableInDb) {
        this.availableInDb = availableInDb;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
