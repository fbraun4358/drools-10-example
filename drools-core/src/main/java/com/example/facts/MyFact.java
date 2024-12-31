package com.example.facts;

public class MyFact<T> {
	
	private final T myT;

	public MyFact(T t) {
		this.myT = t;
	}

	public T getMyT() {
		return myT;
	}
}
