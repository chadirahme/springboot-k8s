package com.chadi.k8s;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

//@SpringBootTest
class SpringbootK8sApplicationTests {

	//@Test
	void contextLoads() {
	}


	//@Test
	public static void main(String[] args) {
		IntStream.range(1, 3).reduce((x, y) -> x + y)
				.ifPresent(s -> System.out.println(s));

		int res= IntStream.range(1, 3).reduce(2, (x, y) -> x + y);
		System.out.println("res>> " + res);
	}
}
