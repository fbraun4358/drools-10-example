package com.example.kei;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class KieHelperFactory {

	private Map<String, KieHelperLoader> loaderCache;
	private Map<String, KieHelper> helperCache;
	
	public KieHelperFactory(String string, Collection<NetworkConfiguration> networkConfigs) {
		this.loaderCache = new HashMap<>();
		this.helperCache = new HashMap<>();
		
		for(var config : networkConfigs){
			this.addLoaderForConifg(config);
		}
	}
	
	protected abstract void addLoaderForConifg(NetworkConfiguration config);
	
	protected void addToLoaderCache(String networkName, KieHelperLoader loader){
		this.loaderCache.put(networkName, loader);
	}
	
	public KieHelper getKieHelperFor(String networkName) {
		KieHelper helper = this.helperCache.get(networkName);
		if(helper != null){
			return helper;
		}
		
		KieHelperLoader loader = this.loaderCache.get(networkName);
		if(loader != null){
			helper = loader.load();
			this.helperCache.put(networkName, helper);
			return helper;
		} else {
			throw new IllegalArgumentException(networkName + " is not defined in the loadMe file");
		}
	}
}
