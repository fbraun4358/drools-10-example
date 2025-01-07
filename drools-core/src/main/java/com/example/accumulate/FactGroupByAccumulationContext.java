package com.example.accumulate;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import com.example.facts.MyFact;


public class FactGroupByAccumulationContext<K, R extends MyFact<?>> implements AccumulationContext<R> {
	
	/**
	* 
	*/
	private static final long serialVersionUID = 6671705408794253606L;

	public static Class<?> getResultType() {
		return Map.class;
	}
	
	private final Map<K, Set<R>> groupings = new HashMap<>();
	
	private final Function<R, K> keyExtractor;
	
	public FactGroupByAccumulationContext(Function<R, K> keyExtractor) {
		this.keyExtractor = keyExtractor;
	}
	
	@Override
	public void init() {
		this.groupings.clear();
	}
	
	public boolean store(R result){
		
		if(result != null) {
			
			K key = this.keyExtractor.apply(result);
			
			return this.groupings.computeIfAbsent(key, k -> new HashSet<>())
					.add(result);
		}

		return false;
	}
	
	public boolean remove(R result){
		
		boolean removed = false;
		
		if(result != null) {
			
			K key = this.keyExtractor.apply(result);

			Set<R> grouping = this.groupings
					.getOrDefault(key, Collections.emptySet());
			
			removed = grouping.remove(result);
			
			this.groupings.computeIfPresent(key, (k, v) -> v.isEmpty() ? null : v );
		}
		
		return removed;
	}

	public Map<K, Set<R>> getGrouping() {
		return this.groupings;
	}
}