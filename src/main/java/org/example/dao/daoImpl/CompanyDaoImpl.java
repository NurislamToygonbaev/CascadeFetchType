package org.example.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import org.example.config.ConfigUtil;
import org.example.dao.CompanyDao;
import org.example.entities.Address;
import org.example.entities.Company;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompanyDaoImpl implements CompanyDao, AutoCloseable {
    private final EntityManagerFactory entityManagerFactory = ConfigUtil.getEntityManagerFactory();
    @Override
    public String saveCompany(Company newCompany) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(newCompany);
            entityManager.getTransaction().commit();
            return newCompany.getName() + " successfully saved";
        } catch (Exception e){
            if (entityManager != null && entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            return "Failed: " + e.getMessage();
        }
    }

    @Override
    public String saveCompany(Long addressId, Company newCompany) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Address address = entityManager.find(Address.class, addressId);
            if (address == null){
                return "Address with id: " + addressId + " not found!";
            }
            newCompany.setAddress(address);
            entityManager.persist(newCompany);
            entityManager.getTransaction().commit();
            return newCompany.getName() + " successfully saved";
        } catch (Exception e){
            if (entityManager != null && entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            return "Failed: " + e.getMessage();
        }
    }

    @Override
    public Optional<Company> findCompanyById(Long companyId) {
        EntityManager entityManager = null;
        Company company = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            company = entityManager.createQuery("select c from Company c where id =:companyId", Company.class)
                            .setParameter("companyId", companyId)
                            .getSingleResult();
            entityManager.getTransaction().commit();
        } catch (NoResultException e){
            System.out.println("Company with id " + companyId + " not found.");
        } catch (Exception e){
            if (entityManager != null && entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            System.out.println("Failed: "+ e.getMessage());
        }
        return Optional.ofNullable(company);
    }

    @Override
    public List<Company> findAllCompanies() {
        EntityManager entityManager = null;
        List<Company> companies = new ArrayList<>();
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            companies = entityManager.createQuery("select c from Company c", Company.class)
                    .getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e){
            if (entityManager != null && entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            System.out.println("Failed: "+ e.getMessage());
        }
        return companies;
    }

    @Override
    public String updateCompanyById(Long companyId, Company newCompany) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Company company = entityManager.find(Company.class, companyId);
            if (company == null){
                return "Company with id: "+ companyId +" not found";
            }
            company.setName(newCompany.getName());
            entityManager.getTransaction().commit();
            return company.getName() + " successfully updated!!!";
        }catch (Exception e){
            if (entityManager != null && entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            return "Failed: "+ e.getMessage();
        }
    }

    @Override
    public String deleteCompanyById(Long companyId) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Company companyToRemove = entityManager.find(Company.class, companyId);
            if (companyToRemove == null){
                return "Company with id: "+companyId+" nor found!";
            }

            entityManager.remove(companyToRemove);
            entityManager.getTransaction().commit();
            return companyToRemove.getName() + " successfully deleted!!!";
        }catch (Exception e){
            if (entityManager != null && entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            return "Failed: "+ e.getMessage();
        }
    }

    @Override
    public String assignCompanyToAddress(Long addressId, Long companyId) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Address addressToAssign = entityManager
                    .createQuery("select a from Address a where id =:addressId", Address.class)
                    .setParameter("addressId", addressId)
                    .getSingleResult();

            Company companyToAssign = entityManager
                    .createQuery("select c from Company c where id =:companyId", Company.class)
                    .setParameter("companyId", companyId)
                    .getSingleResult();

            companyToAssign.setAddress(addressToAssign);
            entityManager.getTransaction().commit();
            return "successfully assigned!!!";
        }catch (NoResultException e){
            return addressId +" or "+ companyId + " not found.";
        } catch (Exception e){
            if (entityManager != null && entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            return "Failed: "+ e.getMessage();
        }
    }

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }
}
