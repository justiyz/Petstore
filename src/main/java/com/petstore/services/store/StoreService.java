package com.petstore.services.store;


import com.petstore.data.model.Store;

import java.util.List;

//This class is provides all the services our Store interface provides
public interface StoreService {

    Store saveStore(Store store);
    Store updateStore(Store store);
    Store findStoreById(Integer id);
    void deleteStoreById(Integer id);
    List<Store> findAllStores();
}
