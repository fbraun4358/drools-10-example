package com.example.kei;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class ClasspathBasedKieHelper implements KieHelper {
	
	private final String networkName;
	private transient KieBase kieBase;
	
	public ClasspathBasedKieHelper(String networkName) {
		KieServices services = KieServices.Factory.get();
		KieContainer container = services.getKieClasspathContainer(this.getClass().getClassLoader());
		
		this.networkName = networkName;
		this.kieBase = container.getKieBase(networkName);
	}

	@Override
	public KieSession newKieSession() {
		return this.kieBase.newKieSession();
	}

	@Override
	public String getStartProcessId() {
		return this.networkName;
	}

	@Override
	public String getNetworkName() {
		return this.networkName;
	}

}
