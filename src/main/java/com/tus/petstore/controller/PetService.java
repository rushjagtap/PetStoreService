package com.tus.petstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tus.petstore.dto.OwnerRepository;
import com.tus.petstore.dto.PetRepository;
import com.tus.petstore.entity.Owner;
import com.tus.petstore.entity.Pet;



@Service
public class PetService {
	@Autowired
	 PetRepository petRepository;
	
	@Autowired
	 OwnerRepository ownerRepository;
		
	public List<Pet> getAllPet()
	{
		return petRepository.findAll();
	}
	
	public Optional<Pet> getPet(int id)
	{
		return petRepository.findById(id);
	}
	
	public List<Pet> getPet(String name)
	{
		return petRepository.findByName(name);
	}
	
	public Pet addPet(Pet pet)
	{
		return petRepository.save(pet);
	}
	
	public Pet deletePet(int id)
	{
		
		Optional<Pet> optionalPet = petRepository.findById(id);
		if(optionalPet.isPresent())
		{
			Pet existingPet = optionalPet.get();
			petRepository.deleteById(id);
			return existingPet;
		}
		else
			return null;
	}
	
	public Pet updatePet(int id, Pet pet)
	{
		
		Optional<Pet> optionalPet = petRepository.findById(id);
		if(optionalPet.isPresent())
		{
			Pet existingPet = optionalPet.get();
			existingPet.setBirthdate(pet.getBirthdate());
			existingPet.setName(pet.getName());
			existingPet.setType(pet.getType());
			existingPet.setVisits(pet.getVisits());
		
			return petRepository.save(existingPet);
		}
		else
			return null;
	}

	public List<Owner> getAllOwners()
	{
		return ownerRepository.findAll();
	}
	
	public Optional<Owner> getOwner(int id)
	{
		return ownerRepository.findById(id);
	}
	
	public List<Owner> getOwner(String name)
	{
		
		return ownerRepository.findByName(name);
	}
	
	public Owner addOwner(Owner owner)
	{
		return ownerRepository.save(owner);
	}
	
	public Owner deleteOwner(int id)
	{
		Owner existingOwner=null;
		Optional<Owner> optionalOwner = ownerRepository.findById(id);
		
		if (optionalOwner.isPresent())
		{
			 existingOwner = optionalOwner.get();
			ownerRepository.deleteById(id);
			
		}
		
		return existingOwner;
		
	}
	
	public Owner updateOwner(int id, Owner owner)
	{
		
		Optional<Owner> optionalOwner = ownerRepository.findById(id);
		
		if(optionalOwner.isPresent())
		{
			Owner existingOwner = optionalOwner.get();
			existingOwner.setAddress(owner.getAddress());
			existingOwner.setCity(owner.getCity());
			existingOwner.setContactNumber(owner.getContactNumber());
			existingOwner.setName(owner.getName());
			existingOwner.setOwnerId(owner.getOwnerId());
			existingOwner.setPets(owner.getPets());
			return ownerRepository.save(existingOwner);
		}
		else
			return null;
		
		
	}
	

}
