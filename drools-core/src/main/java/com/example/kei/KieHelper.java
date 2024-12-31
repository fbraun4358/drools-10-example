package com.example.kei;

import org.kie.api.runtime.KieSession;

public interface KieHelper {

	public KieSession newKieSession();
	
	public String getStartProcessId();
	
	public String getNetworkName();
}

