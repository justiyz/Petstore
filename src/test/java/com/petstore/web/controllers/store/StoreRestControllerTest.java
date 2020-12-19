package com.petstore.web.controllers.store;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petstore.data.model.Store;
import com.petstore.services.pet.PetService;
import com.petstore.services.store.StoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:db/insert.sql"})
class StoreRestControllerTest {

    @Autowired
    StoreService storeService;

    @Autowired
    PetService petService;

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper;

    Store store;



    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        store = new Store();
    }








}