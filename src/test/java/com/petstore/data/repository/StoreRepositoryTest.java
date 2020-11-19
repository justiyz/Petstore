package com.petstore.data.repository;

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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class StoreRepositoryTest {

    @Autowired
    StoreRepository storeRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void saveStoreToDatabaseTest(){
        //create store
        Store store = new Store();
        store.setName("The Pet Store");
        store.setContactNo("09099887766");
        store.setLocation("Ikeja");

        log.info("store object  before adding --> {}", store);

        //save store to database
        storeRepository.save(store);

        log.info("store object  after adding --> {}", store);
        assertThat(store.getId()).isNotNull();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void findAllSavedStoresFromDBTest(){
        List<Store> stores = storeRepository.findAll();
        log.info("stores retrieved from the database --> {}", stores);
        assertThat(stores).isNotNull();
        assertThat(stores.size()).isEqualTo(5);

        log.info("Pets in store with ID 21 --> {}", stores.get(0).getPetList());
    }

    @Test
    public void updateAnExistingStoreDetailsTest(){
        Store elite = storeRepository.findById(22).orElse(null);
        assertThat(elite).isNotNull();
        assertThat(elite.getLocation()).isEqualTo("Lagos");

        elite.setLocation("Kaduna");
        storeRepository.save(elite);
        log.info("store object updated --> {}", elite);
        assertThat(elite.getLocation()).isEqualTo("Kaduna");

    }

    void deleteStoreTest(){

        assertThat(storeRepository.existsById(23)).isTrue();
        storeRepository.deleteById(23);
        assertThat(storeRepository.existsById(23)).isFalse();
    }


    void findAllPetsInAStoreTest(){

        Store store = storeRepository.findById(21).orElse(null);
        assertThat(store).isNotNull();
        assertThat(store.getPetList()).isNotNull();
        log.info("Pets in the store of ID 21 --> {}", store.getPetList());
    }

    @Test
    void findStoreByName(){
        Store store = storeRepository.findByName("our store");

        log.info("Store object --> {}", store);
        assertThat(store).isNotNull();
    }



}