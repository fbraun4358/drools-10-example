package com.example.kei;

import java.util.Collection;

public class ClasspathBasedKieHelperFactory extends KieHelperFactory {

	public ClasspathBasedKieHelperFactory(Collection<NetworkConfiguration> networkConfigs) {
		super("", networkConfigs);
	}

	@Override
	protected void addLoaderForConifg(NetworkConfiguration config) {
		var networkName = config.getName();
		
		var loader = new ClasspathBasedKieHelperLoader(networkName);
		
		this.addToLoaderCache(networkName, loader);
	}

}
