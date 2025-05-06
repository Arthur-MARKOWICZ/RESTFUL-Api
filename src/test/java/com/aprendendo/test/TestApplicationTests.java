package com.aprendendo.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = TestApplication.class)
@ActiveProfiles("teste")
class TestApplicationTests {

	@Test
	void contextLoads() {
	}

}
