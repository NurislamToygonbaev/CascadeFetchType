package org.example.service;

import org.example.entities.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    String saveAddress(Address newAddress);
    Optional<Address> findAddressById(Long addressId);
    List<Address> getAllAddresses();
    String updateAddressById(Long addressId, Address newAddress);
    String deleteAddressById(Long addressId);
}
