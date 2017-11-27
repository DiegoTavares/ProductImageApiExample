package com.apiapp;

import java.util.HashSet;
import java.util.Set;


public class SetCallHandler {
	private final Set<String> internalSet = new HashSet();

	public boolean add(final String value){
		return internalSet.add(value);
	}

	public void add(final Set<String> values) {
		internalSet.addAll(values);
	}

	public Set<String> getSetCopy() {
		return new HashSet(internalSet);
	}
}
