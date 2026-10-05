package com.udea.lab2v2026;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
//import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.beans.factory.annotation.Autowired;
import tools.jackson.databind.JsonNode;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest // llama implicitamente los los casos de prueba de la clase Lab2v2026ApplicationTests
class Lab2v2026ApplicationTests {
	@Autowired
	DataController dataController; //inyectar la dependencia del controlador para poder probar sus métodos

	@Test
	void health() {
		assertEquals("HEALTH CHECK OK!", dataController.healthCheck()); //valos esperado se compara con el del controlador si coinciden pasa la prueba
	}

	@Test
	void version() {
		assertEquals("The actual version is 1.0.0", dataController.version());
	}

	@Test
	void nationLength() { //longitud de los datos
		Integer nationsLength = dataController.getRandomNations().size();
		assertEquals(10, nationsLength); //se espera que el tamaño de la lista de naciones sea 10
	}

	@Test
	void currenciesLength() {
		Integer currenciesLength = dataController.getRandomCurrencies().size();
		assertEquals(20, currenciesLength);
	}

	@Test
	public void testRandomCurrenciesCodeFormat() { //
		DataController controller = new DataController();
		JsonNode response = controller.getRandomCurrencies();

		for (int i = 0; i < response.size(); i++) {
			JsonNode currency = response.get(i);
			String code = currency.get("code").asText();
			assertTrue(code.matches("[A-Z]{3}")); // codigo regular
		}
	}

	@Test
	public void testRandomNationsPerformance() { // tiempo de generacion de tiempo de respuesta de la peticion
		DataController controller = new DataController();
		long startTime = System.currentTimeMillis();

		controller.getRandomNations();

		long endTime = System.currentTimeMillis();
		long executionTime = endTime - startTime;
		System.out.println(executionTime);

		// Assert that execution time is within acceptable limits
		assertTrue(executionTime < 2000); // 2 second threshold
	}
	@Test
	void aviationsLength() {
		Integer aviationsLength = dataController.getRandomnAviation().size();
		assertEquals(20, aviationsLength);
	}
}
