package com.acme.payment.enums;

import java.util.HashMap;
import java.util.Map;

public enum Provider {

	ZIRAAT, GARANTI;
	

	private static Map<String, Provider> nameMap = new HashMap<>();
	static {
		for (Provider r : values()) {
			nameMap.put(r.name(), r);
		}
	}

	public static Provider fromName(String name) {
		return nameMap.get(name);
	}

}
