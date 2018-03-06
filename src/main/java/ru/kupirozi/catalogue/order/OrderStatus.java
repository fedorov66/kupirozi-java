package ru.kupirozi.catalogue.order;

/**
 * Created by fedorov on 16.02.2018.
 */
public enum  OrderStatus {

    NOT_PAID("Новый"),
	PAID("Оплаченный"),
	COMPLETED("Завершенный"),
	IN_PROGRESS("Формируется"),
	DIGGED("Выкопка"),
	DIGGED_1("Выкопка 1"),
	DIGGED_2("Выкопка 2"),
	DIGGED_3("Выкопка 3"),
	DIGGED_4("Выкопка 4"),
	STAMBS("Штамбы"),
	STAMBS_AUTUMN("Штамбы осень"),
	CANCELED("Отмененный"),
	DEBT("Долг"),
	DEBT_2("Долг 2");

	private String orderStatusName;

	private OrderStatus(final String name) {
		orderStatusName = name;
	}

	@Override
	public String toString() {
		return orderStatusName;
	}

}
