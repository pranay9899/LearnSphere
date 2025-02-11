package com.learnsphere.service;

import com.learnsphere.entity.Address;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface AddressService {
    public void setAddress(Address address);
    public ArrayList<Address> getAddresses();
    public void setAddresses(ArrayList<Address> addresses);
    public ArrayList<Address> getAddressesByName(String name);
    public ArrayList<Address> getAddressesByCity(String city);
    public ArrayList<Address> getAddressesByCountry(String country);
    public ArrayList<Address> getAddressesByState(String state);
    public ArrayList<Address> getAddressesByZipCode(String zipCode);
}
