package com.tus.petstore;



import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import com.tus.petstore.entity.Owner;
import com.tus.petstore.entity.Pet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void greetingShouldReturnDefaultMessage() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
				String.class)).contains("Hello, World");
	}
	
	@Test
    public void testAddPetSuccess() throws URISyntaxException 
    {
        final String baseUrl = "http://localhost:"+port+"/pets";
        URI uri = new URI(baseUrl);
        Pet pet = new Pet();
        pet.setName("Tom");
        pet.setType("CAT");
        
         
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");      
 
        HttpEntity<Pet> request = new HttpEntity<>(pet, headers);
         
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
         
        //Verify request succeed
        assertEquals(201, result.getStatusCodeValue());
    }
	
	@Test
    public void testAddOwnerSuccess() throws URISyntaxException 
    {
        final String baseUrl = "http://localhost:"+port+"/owners";
        URI uri = new URI(baseUrl);
        Owner owner = new Owner();
        owner.setName("Sam");
        owner.setAddress("Dalys apartment");
        owner.setCity("Athlone");
        owner.setContactNumber("00000000");
    
        
         
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");      
 
        HttpEntity<Owner> request = new HttpEntity<>(owner, headers);
         
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
         
        //Verify request succeed
        assertEquals(201, result.getStatusCodeValue());
    }
	
	@Test
    public void testAddPetMissingParamerets() throws URISyntaxException 
    {
		final String baseUrl = "http://localhost:"+port+"/pets";
        URI uri = new URI(baseUrl);
        Pet pet = new Pet();
     
        pet.setType("CAT");
        
         
        HttpHeaders headers = new HttpHeaders();
         
 
        HttpEntity<Pet> request = new HttpEntity<>(pet, headers);
         
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
         
      //Verify bad request and missing header
        assertEquals(400, result.getStatusCodeValue());
        assertEquals(true, result.getBody().contains("Bad Request"));
    }
}