package com.example.accumulate;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import com.example.facts.MyFact;

public class FactGroupByValueAccumulationFunction<K, R extends MyFact<K>>
		extends BaseAccumulationFunction<FactGroupByAccumulationContext<K, R>, R> {
	
	public Function<R, K> keyFunction(){
		return MyFact::getMyT;
	}

	@Override
	public FactGroupByAccumulationContext<K, R> createContext() {
		return new FactGroupByAccumulationContext<>(this.keyFunction());
	}

	@Override
	public Map<K, Set<R>> getResult(FactGroupByAccumulationContext<K, R> context) {
		return context.getGrouping();
	}
	
	@Override
	public Class<?> getResultType() {
		return FactGroupByAccumulationContext.getResultType();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void accumulate(FactGroupByAccumulationContext<K, R> context, Object value) {
		
		context.store((R) value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void reverse(FactGroupByAccumulationContext<K, R> context, Object value) {
		context.remove((R) value);
	}
}