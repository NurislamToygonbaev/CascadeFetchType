package org.example.dao;

import org.example.entities.Address;

import java.util.List;
import java.util.Optional;

public interface AddressDao {
    String saveAddress(Address newAddress);
    Optional<Address> findAddressById(Long addressId);
    List<Address> getAllAddresses();
    String updateAddressById(Long addressId, Address newAddress);
    String deleteAddressById(Long addressId);
}
