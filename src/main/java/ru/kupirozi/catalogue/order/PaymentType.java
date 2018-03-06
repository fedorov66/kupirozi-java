package ru.kupirozi.catalogue.order;

/**
 * Created by fedorov on 16.02.2018.
 */
public enum PaymentType {

    CASH("На карту Сбербанка", "card_sberbank"),
    POST_TRANSFER("Почтовый перевод", "post_transfer"),
    SBERBANK_CART("Оплата наличными (при самовывозе)", "cash");

    private String name;
    private String code;

    private PaymentType(final String name, final String code) {
        this.name = name;
        this.code = code;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getInternalCode() {
        return code;
    }

    public PaymentType getByCode(final String code) {
        for (PaymentType pt : PaymentType.values()) {
            if (code.equals(pt.getInternalCode())) {
                return pt;
            }
        }
        return null;
    }
}
