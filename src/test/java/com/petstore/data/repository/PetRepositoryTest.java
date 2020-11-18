package com.petstore.data.repository;

import com.petstore.data.model.Gender;
import com.petstore.data.model.Pet;
import com.petstore.data.model.Store;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@Sql(scripts = {"classpath:db/insert.sql"}) //runs db file from the path
@Slf4j
@SpringBootTest
class PetRepositoryTest {

    @Autowired
    PetRepository petRepository;

    @Autowired
    StoreRepository storeRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void whenPetIsSaved_thenReturnAPetId(){
        Pet pet = new Pet();
        pet.setName("Jack");
        pet.setAge(2);
        pet.setBreed("Dog");
        pet.setColor("Black");
        pet.setPetSex(Gender.MALE);

        //call repository save method
        petRepository.save(pet);

        assertThat(pet.getId()).isNotNull();
        log.info("pet instance after adding --> {}", pet);
    }

    @Test
    @Rollback(value = false) //removes the result, value = false keeps the result of saved transaction
    @Transactional //allows multiple operations with the db, keep transaction open
    public void whenStoreIsMappedToPet_thenForeignKeyIsPresent(){
        //cascading allows us to save a parent and a child for just an instance(save both pet and store)
        //create pet
        Pet pet = new Pet();
        pet.setName("Jack");
        pet.setAge(2);
        pet.setBreed("Dog");
        pet.setColor("Black");
        pet.setPetSex(Gender.MALE);

        //call repository save method
        log.info("pet instance after adding --> {}", pet);

        //create store
        Store store = new Store();
        store.setName("Pet Sellers");
        store.setLocation("Yaba");
        store.setContactNo("09099887766");

        pet.setStore(store);

        //map to store
        petRepository.save(pet);

        log.info("Pet instance after adding --> {}", pet);
        log.info("Store instance after adding --> {}", store);

        //assert
        assertThat(pet.getId()).isNotNull();
        assertThat(store.getId()).isNotNull();
        assertThat(pet.getStore()).isNotNull();
    }
    @Test
    @Transactional
    @Rollback(value = false)
    public void whenIAddPetsToAStore_thenICanFetchAListOfAListOfPetsFromStore(){

        //create store
        Store store = new Store();
        store.setName("Pet Sellers");
        store.setLocation("Yaba");
        store.setContactNo("09099887766");

        //create 2 pets
        Pet jack = new Pet();
        jack.setName("Jack");
        jack.setAge(5);
        jack.setBreed("Dog");
        jack.setColor("Black");
        jack.setPetSex(Gender.MALE);
        jack.setStore(store);

        Pet sally = new Pet();
        sally.setName("Sally");
        sally.setAge(2);
        sally.setBreed("Dog");
        sally.setColor("Brown");
        sally.setPetSex(Gender.FEMALE);
        sally.setStore(store);

        log.info("Pet instances after adding --> {}", jack, sally);

        store.addPets(jack);
        store.addPets(sally);

        //save store
        storeRepository.save(store);

        log.info("Store instance after saving --> {}", store);

        //assert for pet id
        assertThat(jack.getId()).isNotNull();

        //assert for store id
        assertThat(sally.getId()).isNotNull();

        //assert that store has pet
        assertThat(store.getPetList());

    }

    @Test
    public void whenFindAllPetIsCalled_thenReturnAllPetsInStore(){

        //find pets from store
        List<Pet> savedPets = petRepository.findAll();
        assertThat(savedPets).isNotEmpty();
        assertThat(savedPets.size()).isEqualTo(7);


        //assert that pets exists
    }

    @Test
    public void updateExistingPetDetailsTest(){
        //fetch a pet
      Pet sally = petRepository.findById(34).orElse(null);

        //assert the field
        assertThat(sally).isNotNull();
        assertThat(sally.getColor()).isEqualTo("brown");

        //update pet field
        sally.setColor("purple");

        //save pet
        petRepository.save(sally);

        log.info("After updating pet object --> {}", sally);

        //assert that update field had changed
        assertThat(sally.getColor()).isEqualTo("purple");
    }


    @Test
    public void whenIdeletePetFromDatabase_thenPetIsDeleted(){
        //check if pet exists
        boolean sally = petRepository.existsById(35);

        //assert that pet exist
        assertThat(sally).isTrue();

        //delete pet
        petRepository.deleteById(35);

        //check if pet exists
        assertThat(petRepository.existsById(35)).isFalse();

    }


}