package com.example.kei;

public class ClasspathBasedKieHelperLoader implements KieHelperLoader {

	private final String networkName;
	
	public ClasspathBasedKieHelperLoader(String networkName) {
		this.networkName = networkName;
	}

	@Override
	public KieHelper load() {
		return new ClasspathBasedKieHelper(this.networkName);
	}

}
