package com.example.controllers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.facts.ErrorFact;
import com.example.facts.IntegerFact;
import com.example.facts.MyFact;
import com.example.facts.StringFact;
import com.example.kei.ClasspathBasedKieHelperFactory;
import com.example.response.Response;

@RestController
public class ClasspathBasedController {
	
	private ClasspathBasedKieHelperFactory kieHelperFactory;

	@Autowired
	public ClasspathBasedController(ClasspathBasedKieHelperFactory factory) {
		this.kieHelperFactory = factory;
	}

	@GetMapping(path = "/classpath", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response group(
			@RequestParam String group) {
		
		var kieHelper = this.kieHelperFactory.getKieHelperFor(group);
		
		var session = kieHelper.newKieSession();
		
		MyFact<?> fact = switch (group) {
			case "GROUP_1" -> new StringFact("1");
			case "GROUP_2" -> new IntegerFact(2);
			default -> throw new IllegalArgumentException("Unexpected value: " + group);
		};
		
		session.insert(fact);
		session.startProcess(group);
		session.fireAllRules();
		
		var rv = session.getObjects().stream()
				.filter(StringFact.class::isInstance)
				.map(StringFact.class::cast)
				.filter(o -> o != fact)
				.collect(Collectors.toList());
		
		if(rv.size() != 1) {
			rv.clear();
			rv.add(ErrorFact.INSTANCE);
		}
		
		var response = rv.get(0);
		
		return new Response(response.getMyT());
	}
}
