package com.tus.petstore;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;


import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.tus.petstore.controller.PetService;
import com.tus.petstore.dto.PetRepository;
import com.tus.petstore.entity.Owner;
import com.tus.petstore.entity.Pet;

@SpringBootTest
public class RepositoryUnitTest {
	
	
	@Autowired
    private PetService petService;

	@Autowired
    private PetRepository petRepo;
    
    @Test
    void addPetTest() throws Exception {
        
        
    	Pet pet = new Pet();
        pet.setName("Tom");
        pet.setType("CAT");
        Pet createdPet = petService.addPet(pet);
        assertEquals(pet.getName(), createdPet.getName());
        assertEquals(pet.getType(), createdPet.getType());
       
        
    }
    
    @Test
    void addOwnerTest() throws Exception {
        
        
    	Owner owner = new Owner();
    	owner.setName("Sam");
    	owner.setCity("Athlone");
    	owner.setContactNumber("0000");
    	owner.setAddress("dalys apartment");
        Owner createdOwner = petService.addOwner(owner);
      
        assertEquals(owner.getName(), createdOwner.getName());
        assertEquals(owner.getCity(), createdOwner.getCity());
        assertEquals(owner.getAddress(), createdOwner.getAddress());
        assertEquals(owner.getContactNumber(), createdOwner.getContactNumber());
       
        
    }
    
    @Test
    void getPetByNameTest() throws Exception {
        
    	Pet pet = new Pet();
        pet.setName("Tom");
        pet.setType("CAT");
        petService.addPet(pet);
        List<Pet> fetchedPet = petService.getPet("Tom");

        assertEquals("Tom" , fetchedPet.get(0).getName().toString() );
     
       
        
    }
    @Test
    void getPetByNameNegativeTest() throws Exception {
        List<Pet> fetchedPet = petService.getPet("Lucy");
        assertEquals(0, fetchedPet.size());
 
    }
    
    @Test
    void getOwnerByNameNegativeTest() throws Exception {
        List<Owner> fetchedOwner = petService.getOwner("Rick");
        assertEquals(0, fetchedOwner.size());
 
    }



  

}

	
