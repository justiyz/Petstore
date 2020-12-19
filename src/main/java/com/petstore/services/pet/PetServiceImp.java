package com.petstore.services.pet;

import com.petstore.data.model.Pet;
import com.petstore.data.repository.PetRepository;
import com.petstore.web.exceptions.PetDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImp implements PetService {


    @Autowired
    PetRepository petRepository;


    @Override
    public Pet savePet(Pet pet) throws PetDoesNotExistException {
        if (pet == null){
            throw new PetDoesNotExistException("Pet Object cannot be null");
        }
        return petRepository.save(pet);
    }

    @Override
    public Pet updatePet(Pet pet) {
        return null;
    }

    @Override
    public Pet findPetById(Integer id) throws PetDoesNotExistException {
        Pet pet = petRepository.findById(id).orElse(null);

        if(pet != null){
            return pet;
        } else {
            throw new PetDoesNotExistException("Pet with the Id:"+id+" Does not exist");
        }
    }

    @Override
    public List<Pet> findAllPets() {
        return petRepository.findAll();
    }

    @Override
    public void deletePetById(Integer id) throws PetDoesNotExistException {

        try {
            petRepository.deleteById(id);
        } catch (Exception exe){
            throw new PetDoesNotExistException("\"Pet with the id:\"+ id");
        }
    }
}
