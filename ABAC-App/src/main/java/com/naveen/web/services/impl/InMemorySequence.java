package com.naveen.web.services.impl;

public class InMemorySequence {
	private Integer currentValue = new Integer(Integer.valueOf(0));
	public Integer increment() {
		synchronized(this) {
			currentValue = currentValue + 1;
			return currentValue;
		}
	}
}
