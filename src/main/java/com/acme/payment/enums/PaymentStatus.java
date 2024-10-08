package com.acme.payment.enums;

import java.util.HashMap;
import java.util.Map;

public enum PaymentStatus {

	INITIALIZED, PENDING, SUCCESS, FAILED, FRAUD;

	private static Map<String, PaymentStatus> nameMap = new HashMap<>();
	static {
		for (PaymentStatus r : values()) {
			nameMap.put(r.name(), r);
		}
	}

	public static PaymentStatus fromName(String name) {
		return nameMap.get(name);
	}

}
