package com.derkach.boot.hello_back_end;

import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.derkach.boot.hello_back_end.contacts.Contacts;
import com.derkach.boot.hello_back_end.exceptions.ErrorDetails;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

	private final static String userServiceUrl = "http://localhost:";

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	int expectedResult = 520000 - 520;

	@Test
	public final void test() {

		ResponseEntity<Contacts> response = restTemplate.getForEntity(
				userServiceUrl + port + "/hello/contacts?nameFilter=.*Theodor Blaskett.*", Contacts.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
//		System.out.println(response.getHeaders().getContentType());
		assertEquals(response.getHeaders().getContentType().toString(), MediaType.APPLICATION_JSON_UTF8_VALUE);
		assertThatJson(response.getBody()).node("contacts").isArray().ofLength(expectedResult);

		ResponseEntity<ErrorDetails> responseError = restTemplate.getForEntity(userServiceUrl + port + "/hello/contacts", ErrorDetails.class);

		assertEquals(HttpStatus.BAD_REQUEST, responseError.getStatusCode());
//		System.out.println(responseError.getHeaders().getContentType());
		assertEquals(responseError.getHeaders().getContentType().toString(), MediaType.APPLICATION_JSON_UTF8_VALUE);
		assertThatJson(responseError.getBody()).node("message")
				.isEqualTo("Required String parameter 'nameFilter' is not present");

		responseError = restTemplate.getForEntity(userServiceUrl + port + "/hello/contact", ErrorDetails.class);

		assertEquals(HttpStatus.NOT_FOUND, responseError.getStatusCode());
//		System.out.println(responseError.getHeaders().getContentType());
		assertEquals(responseError.getHeaders().getContentType().toString(), MediaType.APPLICATION_JSON_UTF8_VALUE);
		assertThatJson(responseError.getBody()).node("message").isEqualTo("No handler found for GET /hello/contact");
	}

}
