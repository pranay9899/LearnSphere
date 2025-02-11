package com.learnsphere.service;

import com.learnsphere.entity.Address;
import com.learnsphere.repository.AddressRepository;

import java.util.ArrayList;

public class AddressServiceIMpl implements AddressService{
    AddressRepository addressRepository;

    public AddressServiceIMpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void setAddress(Address address) {
        addressRepository.save(address);
    }

    @Override
    public ArrayList<Address> getAddresses() {
        return (ArrayList<Address>) addressRepository.findAll();
    }

    @Override
    public void setAddresses(ArrayList<Address> addresses) {
        addressRepository.saveAll(addresses);
    }

    @Override
    public ArrayList<Address> getAddressesByName(String name) {
        return addressRepository.findByName(name);
    }

    @Override
    public ArrayList<Address> getAddressesByCity(String city) {
        return addressRepository.findByCity(city);
    }

    @Override
    public ArrayList<Address> getAddressesByCountry(String country) {
        return addressRepository.findByCountry(country);
    }

    @Override
    public ArrayList<Address> getAddressesByState(String state) {
        return addressRepository.findByState(state);
    }

    @Override
    public ArrayList<Address> getAddressesByZipCode(String zipCode) {
        return addressRepository.findByZip(zipCode);
    }
}
