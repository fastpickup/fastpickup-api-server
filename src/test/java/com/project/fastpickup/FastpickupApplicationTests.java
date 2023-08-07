package com.project.fastpickup;

/*
 * Date   : 2023.07.26
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */
import java.sql.Connection;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

@Log4j2
@SpringBootTest
class FastpickupApplicationTests {

	@Autowired
	private DataSource dataSource;

	@Test
	public void connetionTest() {
		try (Connection connection = dataSource.getConnection()) {
			log.info("Is Ok Database Connetion");
		} catch (Exception e) {
			log.info("Find Some Erros");
		}
	}
}
