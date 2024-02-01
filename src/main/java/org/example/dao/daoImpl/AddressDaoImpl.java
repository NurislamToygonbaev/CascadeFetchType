package org.example.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import org.example.config.ConfigUtil;
import org.example.dao.AddressDao;
import org.example.entities.Address;
import org.example.entities.Company;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddressDaoImpl implements AddressDao, AutoCloseable {
    private final EntityManagerFactory entityManagerFactory = ConfigUtil.getEntityManagerFactory();

    @Override
    public String saveAddress(Address newAddress) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(newAddress);
            entityManager.getTransaction().commit();
            return newAddress.getCountry() + " successfully saved!";
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return "Failed: " + e.getMessage();
        }
    }

    @Override
    public Optional<Address> findAddressById(Long addressId) {
        EntityManager entityManager = null;
        Address address = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            address = entityManager.find(Address.class, addressId);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Failed: " + e.getMessage());
        }
        return Optional.ofNullable(address);
    }


    @Override
    public List<Address> getAllAddresses() {
        EntityManager entityManager = null;
        List<Address> addresses = new ArrayList<>();
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            addresses = entityManager.createQuery("select a from Address a", Address.class)
                    .getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Failed: " + e.getMessage());
        }
        return addresses;
    }

    @Override
    public String updateAddressById(Long addressId, Address newAddress) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            Address findAddress = entityManager.find(Address.class, addressId);
            if (findAddress == null) {
                return "Address with id: " + addressId + " not found!";
            }
            findAddress.setCountry(newAddress.getCountry());
            entityManager.getTransaction().commit();
            return findAddress.getCountry() + " successfully updated!!!";
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("error: " + e.getMessage());
            return "Failed to update address.";
        }
    }

    @Override
    public String deleteAddressById(Long addressId) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Address address = entityManager.find(Address.class, addressId);
            if (address == null) {
                return "Address with id " + addressId + " not found!";
            }
            address.setProgrammer(null);
            entityManager.remove(address);
            entityManager.getTransaction().commit();
            return address.getCountry() + " successfully deleted!!!";
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return "Failed: " + e.getMessage();
        }
    }

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }
}
