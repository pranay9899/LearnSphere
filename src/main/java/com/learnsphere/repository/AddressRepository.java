package com.learnsphere.repository;

import com.learnsphere.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
    ArrayList<Address> findByStreet(String name);

    ArrayList<Address> findByCity(String city);

    ArrayList<Address> findByCountry(String country);

    ArrayList<Address> findByState(String state);

    ArrayList<Address> findByZip(String zip);
}
