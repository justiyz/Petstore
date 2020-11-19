package com.petstore.web.controllers.pet;

import com.petstore.data.model.Pet;
import com.petstore.services.pet.PetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pet")
@Slf4j
public class PetRestController {

    @Autowired
    PetService petService;

    @PostMapping("/create")
    public ResponseEntity<?> savePet(@RequestBody Pet pet){

        //log request body
        log.info("Request object --> {}", pet);

        //save request
        try{
            petService.savePet(pet);
        }catch (NullPointerException exe){
            return ResponseEntity.badRequest().body(exe.getMessage());
        }

        return new ResponseEntity<>(pet, HttpStatus.CREATED);
    }
}
