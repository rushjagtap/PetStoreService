package com.tus.petstore.controller;



import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tus.petstore.entity.Pet;
import com.tus.petstore.exception.OwnerNotFoundException;
import com.tus.petstore.exception.PetNotFoundException;
import com.tus.petstore.entity.Owner;

@RestController
public class HomeController {
	
	@Autowired
	 PetService petService;

	@RequestMapping("/")
	public @ResponseBody String greeting() {
		return "Hello, World";
	}
	
	@RequestMapping(value = "/pets" ,method = RequestMethod.GET)
	public List<Pet> getAllPet()
	{
		return petService.getAllPet();
	}
	
	@RequestMapping(value = "/home" ,method = RequestMethod.GET)
	public String welcome()
	{
		return "Welcome to Pet.";
	}
	

	@RequestMapping(value = "/pets/{name}", method = RequestMethod.GET)
	public List<Pet> getPet(@Valid @PathVariable String name)
	{
		
		List<Pet> pet = petService.getPet(name);
		if (!pet.isEmpty())
		{
			return pet;
		}
		else
		{
		 
			throw new PetNotFoundException("Pet not found");
		}
		
	}
	
	
		
	@RequestMapping(value ="/pets", method = RequestMethod.POST)
	public ResponseEntity<Pet> createPet(@Valid @RequestBody Pet pet)
	{
		Pet savedPet = petService.addPet(pet);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPet.getId()).toUri();
		new ResponseEntity<Pet>(savedPet, HttpStatus.CREATED);
		return ResponseEntity.created(location).build();
	}
	
	@RequestMapping(value ="/pets/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Pet> updatePet(@Valid @PathVariable int id, @RequestBody Pet pet)
	{
		Optional<Pet> oPet = petService.getPet(id);
		if (oPet.isPresent())
		{
			
			Pet updatedPet = petService.updatePet(id, pet);
			new ResponseEntity<Pet>(updatedPet,HttpStatus.OK);
			return ResponseEntity.ok(updatedPet);
		}
		else
		{
		 
			throw new PetNotFoundException("Pet not found");
		}
		
	}
	
	@RequestMapping(value ="/pets/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Pet> updatePet(@Valid @PathVariable int id)
	{
		Pet deletedPet = petService.deletePet(id);
		return new ResponseEntity<>(deletedPet,HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/owners" ,method = RequestMethod.GET)
	public List<Owner> getAllOwner()
	{
		return petService.getAllOwners();
	}
	
	@RequestMapping(value = "/owners/{name}", method = RequestMethod.GET)
	public List<Owner> getowner(@Valid @PathVariable String name)
	{
		List<Owner> owner = petService.getOwner(name);
		if (!owner.isEmpty())
		{
			return owner;
		}
		else
		{
		 
			throw new OwnerNotFoundException("Owner not found");
		}
	
	}
	
	
		
	@RequestMapping(value ="/owners", method = RequestMethod.POST)
	public ResponseEntity<Owner> createOwner(@RequestBody Owner owner)
	{
		Owner savedOwner = petService.addOwner(owner);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedOwner.getOwnerId()).toUri();
		new ResponseEntity<Owner>(savedOwner, HttpStatus.CREATED);
		return ResponseEntity.created(location).build();
	}
	
	@RequestMapping(value ="/owners/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Owner> updateOwner(@PathVariable int id, @RequestBody Owner owner)
	{
		
		Optional<Owner> oOwner = petService.getOwner(id);
		if (oOwner.isPresent())
		{
			
			Owner updatedOwner = petService.updateOwner(id, owner);
			new ResponseEntity<Owner>(updatedOwner,HttpStatus.OK);
			return ResponseEntity.ok(updatedOwner);
		}
		else
		{
		 
			throw new OwnerNotFoundException("Owner not found");
		}
		
	}
	
	@RequestMapping(value ="/owners/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Owner> updateOwner(@PathVariable int id)
	{
		Owner deletedOwner = petService.deleteOwner(id);
		return new ResponseEntity<>(deletedOwner,HttpStatus.OK);
	}

}
