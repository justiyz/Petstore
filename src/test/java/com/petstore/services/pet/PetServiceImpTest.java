package com.petstore.services.pet;

import com.petstore.data.model.Pet;
import com.petstore.data.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PetServiceImpTest {

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetService petService = new PetServiceImp();

    Pet testPet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testPet = new Pet();
    }

    @Test
    void callTheSavePetToRepositoryTest(){
        //whenever you want to test a method that has a return type, we use the method when()
        when(petRepository.save(testPet)).thenReturn(testPet);
        petService.savePet(testPet);

        verify(petRepository, times(1)).save(testPet);
    }

    @Test
    void mockTheFindByIdRepositoryTest(){
        when(petRepository.findById(2)).thenReturn(Optional.of(testPet));
        petService.findPetById(2);

        verify(petRepository, times(1)).findById(2);
    }


    @Test
    void mockDeletePetRepositoryTest(){
        //whenever you want to test a method that doesnt have a return type, we use the method doNothing()
        doNothing().when(petRepository).deleteById(2);
        petService.deletePetById(2);

        verify(petRepository, times(1)).deleteById(2);
    }

}