package org.example.service.serviceImpl;

import org.example.dao.AddressDao;
import org.example.dao.daoImpl.AddressDaoImpl;
import org.example.entities.Address;
import org.example.service.AddressService;

import java.util.List;
import java.util.Optional;

public class AddressServiceImpl implements AddressService {
    private final AddressDao addressDao = new AddressDaoImpl();
    @Override
    public String saveAddress(Address newAddress) {
        return addressDao.saveAddress(newAddress);
    }

    @Override
    public Optional<Address> findAddressById(Long addressId) {
        return addressDao.findAddressById(addressId);
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressDao.getAllAddresses();
    }

    @Override
    public String updateAddressById(Long addressId, Address newAddress) {
        return addressDao.updateAddressById(addressId, newAddress);
    }

    @Override
    public String deleteAddressById(Long addressId) {
        return addressDao.deleteAddressById(addressId);
    }
}