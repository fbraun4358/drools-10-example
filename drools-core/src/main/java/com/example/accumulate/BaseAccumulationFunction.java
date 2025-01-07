package com.example.accumulate;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.kie.api.runtime.rule.AccumulateFunction;

public abstract class BaseAccumulationFunction<C extends AccumulationContext<R>, R> implements AccumulateFunction<C> {
	
	@Override
	public void init(C context) throws Exception {
		context.init();
	}

	@Override
	public boolean supportsReverse() {
		return true;
	}
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		
	}
}
