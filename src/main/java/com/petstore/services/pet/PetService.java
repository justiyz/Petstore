package com.petstore.services.pet;

import com.petstore.data.model.Pet;

import java.util.List;

//This class is providing all the services our Pet interface provides
public interface PetService {
    Pet savePet(Pet pet);
    Pet updatePet(Pet pet);
    Pet findPetById(Integer id);
    List<Pet> findAllPets();
    void deletePetById(Integer id);




}
