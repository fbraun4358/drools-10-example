package com.example.facts;

public class ErrorFact extends StringFact {
	
	public static final ErrorFact INSTANCE = new ErrorFact();

	private ErrorFact() {
		super("Error!!");
	}
}
