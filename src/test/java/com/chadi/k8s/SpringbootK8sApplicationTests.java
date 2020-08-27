package com.chadi.k8s;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.stream.IntStream;

import static junit.framework.TestCase.assertNull;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.everyItem;

//@SpringBootTest
//@RunWith(SpringRunner.class)
public class SpringbootK8sApplicationTests {

	//https://dzone.com/articles/unit-and-integration-tests-in-spring-boot-2

	//@Test
	void contextLoads() {
	}


	@Test
	public void testAssertNull() {
		assertNull("should be null", null);
	}

	@Test
	public void testNames() throws Exception {
		Assertions.assertThat("one").isEqualTo("one");
	}


	@Test
	public void testAssertNullFail() {
		assertNull("should be null", null);
	}

	@Test
	public void testAssertThatEveryItemContainsString() {
		assertThat(Arrays.asList(new String[] { "fun", "ban", "net" }), everyItem(containsString("n")));
	}


	//@Test
//	public static void main(String[] args) {
//		IntStream.range(1, 3).reduce((x, y) -> x + y)
//				.ifPresent(s -> System.out.println(s));
//
//		int res= IntStream.range(1, 3).reduce(2, (x, y) -> x + y);
//		System.out.println("res>> " + res);
//	}
}
