package com.acme.payment.enums;

import java.util.HashMap;
import java.util.Map;

public enum PaymentType {

	PAYMENT, REFUND;

	private static Map<String, PaymentType> nameMap = new HashMap<>();
	static {
		for (PaymentType r : values()) {
			nameMap.put(r.name(), r);
		}
	}

	public static PaymentType fromName(String name) {
		return nameMap.get(name);
	}

}
