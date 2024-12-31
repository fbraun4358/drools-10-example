package com.example.controllers;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.example.kei.ClasspathBasedKieHelperFactory;
import com.example.kei.NetworkConfiguration;

class ClasspathBasedControllerTest {

	public static Stream<Arguments> data(){
		return Stream.of(
				Arguments.of("GROUP_1", "Group 1 Fired."),
				Arguments.of("GROUP_2", "Group 2 Second Rule Fired.")
		);
	}
	
	@ParameterizedTest
	@MethodSource("data")
	void test(String group, String expected) {
		var config1 = new NetworkConfiguration("GROUP_1");
		var config2 = new NetworkConfiguration("GROUP_2");
		var factory = new ClasspathBasedKieHelperFactory(List.of(config1, config2));
		
		var controller = new ClasspathBasedController(factory);
		
		var response = controller.group(group);
		
		assertThat(response.getResponse())
				.isEqualTo(expected);
	}
}
