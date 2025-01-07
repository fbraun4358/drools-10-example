package com.example.accumulate;

import java.io.Serializable;

public interface AccumulationContext<T> extends Serializable {
	
	void init();
	
	boolean store(T value);
	
	boolean remove(T value);
}
