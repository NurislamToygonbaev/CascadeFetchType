package org.example.dao.daoImpl;

import jakarta.persistence.*;
import org.example.config.ConfigUtil;
import org.example.dao.ProgrammerDao;
import org.example.entities.Address;
import org.example.entities.Programmer;
import org.example.entities.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProgrammerDaoImpl implements ProgrammerDao, AutoCloseable {
    private final EntityManagerFactory entityManagerFactory = ConfigUtil.getEntityManagerFactory();
    @Override
    public String saveProgrammer(Long addressId, Programmer newProgrammer) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Address addressToSaveProgrammer = entityManager.createQuery("select a from Address a where id =:addressId", Address.class)
                    .setParameter("addressId", addressId)
                    .getSingleResult();
            newProgrammer.setAddress(addressToSaveProgrammer);
            entityManager.persist(newProgrammer);
            entityManager.getTransaction().commit();
            return newProgrammer.getFullName() + " successfully saved!!!";
        } catch (NoResultException e){
            return "Address with id " + addressId + " not found.";
        } catch (Exception e){
            if (entityManager != null && entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            return "Failed: " +e.getMessage();
        }
    }

    @Override
    public Optional<Programmer> findProgrammerById(Long programmerId) {
        EntityManager entityManager = null;
        Programmer findProgrammer = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            findProgrammer = entityManager.createQuery("select p from Programmer p where id =:programmerId", Programmer.class)
                    .setParameter("programmerId", programmerId)
                    .getSingleResult();
            entityManager.getTransaction().commit();
        } catch (NoResultException e){
            System.out.println("Programmer with id: "+programmerId+" not found!");
        } catch (Exception e){
            if (entityManager != null && entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            System.out.println("Failed: "+e.getMessage());
        }
        return Optional.ofNullable(findProgrammer);
    }

    @Override
    public List<Programmer> findAllProgrammers() {
        EntityManager entityManager = null;
        List<Programmer> programmers = new ArrayList<>();
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            programmers = entityManager.createQuery("select p from Programmer p", Programmer.class)
                    .getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Failed: "+e.getMessage());
        }
        return programmers;
    }

    @Override
    public String updateProgrammerById(Long programmerId, Programmer newProgrammer) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Programmer oldProgrammer = entityManager.find(Programmer.class, programmerId);
            if (oldProgrammer == null){
                return "Programmer with id: "+programmerId+" not found!";
            }
            oldProgrammer.setFullName(newProgrammer.getFullName());
            oldProgrammer.setEmail(newProgrammer.getEmail());
            entityManager.getTransaction().commit();
            return newProgrammer.getFullName()+" successfully saved";
        }catch (Exception e){
            if (entityManager != null && entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            return "Failed: "+e.getMessage();
        }
    }

    @Override
    public String deleteProgrammerById(Long programmerId) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Programmer programmer = entityManager.find(Programmer.class, programmerId);
            if (programmer == null){
                return "Programmer with id: "+programmerId+" not found!";
            }
            for (Project project : programmer.getProjects()) {
                project.getProgrammers().remove(programmer);
            }
            entityManager.remove(programmer);
            entityManager.getTransaction().commit();
            return programmer.getFullName()+" successfully deleted";
        }catch (Exception e){
            if (entityManager != null && entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            return "Failed: "+e.getMessage();
        }
    }

    @Override
    public String assignProgrammerToProject(Long programmerId, Long projectId) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Programmer programmerToAssign = entityManager
                    .createQuery("select p from Programmer p where id =:programmerId", Programmer.class)
                    .setParameter("programmerId", programmerId)
                    .getSingleResult();

            Project projectToAssign = entityManager
                    .createQuery("select p from Project p where id =:projectId", Project.class)
                    .setParameter("projectId", projectId)
                    .getSingleResult();
            projectToAssign.getProgrammers().add(programmerToAssign);
            programmerToAssign.getProjects().add(projectToAssign);
            entityManager.getTransaction().commit();
            return "successfully assigned";
        } catch (NoResultException e){
            return programmerId +" or "+ projectId + " not found.";
        } catch (Exception e){
            if (entityManager != null && entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            return "Failed: "+e.getMessage();
        }
    }

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }
}
