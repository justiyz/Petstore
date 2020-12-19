package com.petstore.services.pet;

import com.petstore.data.model.Pet;
import com.petstore.web.exceptions.PetDoesNotExistException;

import java.util.List;

//This class is providing all the services our Pet interface provides
public interface PetService {
    Pet savePet(Pet pet) throws PetDoesNotExistException;
    Pet updatePet(Pet pet);
    Pet findPetById(Integer id) throws PetDoesNotExistException;
    List<Pet> findAllPets();
    void deletePetById(Integer id) throws PetDoesNotExistException;




}
