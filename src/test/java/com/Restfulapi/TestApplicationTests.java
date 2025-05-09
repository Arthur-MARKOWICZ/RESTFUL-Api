package com.Restfulapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = RestfulApiApplication .class)
@ActiveProfiles("teste")
class TestApplicationTests {

	@Test
	void contextLoads() {
	}

}
