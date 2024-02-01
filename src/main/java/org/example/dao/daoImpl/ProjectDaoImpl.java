package org.example.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import org.example.config.ConfigUtil;
import org.example.dao.ProjectDao;
import org.example.entities.Company;
import org.example.entities.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectDaoImpl implements ProjectDao, AutoCloseable {
    private final EntityManagerFactory entityManagerFactory = ConfigUtil.getEntityManagerFactory();
    @Override
    public String saveProject(Project newProject) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(newProject);
            entityManager.getTransaction().commit();
            return newProject.getTitle()+" successfully saved!!!";
        } catch (Exception e){
            if (entityManager != null && entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            return "Failed: "+e.getMessage();
        }
    }

    @Override
    public String saveProject(Long companyId, Project newProject) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Company companyToSaveProject = entityManager.createQuery("select c from Company c where id =:companyId", Company.class)
                    .setParameter("companyId", companyId)
                    .getSingleResult();
            newProject.setCompany(companyToSaveProject);
            entityManager.persist(newProject);
            entityManager.getTransaction().commit();
            return newProject.getTitle()+" successfully saved!!!";
        }catch (NoResultException e){
            return "Company with id " + companyId + " not found!";
        } catch (Exception e){
            if (entityManager != null && entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            return "Failed: "+e.getMessage();
        }
    }

    @Override
    public Optional<Project> findProjectById(Long projectId) {
        EntityManager entityManager = null;
        Project project = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            project = entityManager.createQuery("select p from Project p where id =:projectId", Project.class)
                            .setParameter("projectId", projectId)
                                    .getSingleResult();

            entityManager.getTransaction().commit();
        }catch (NoResultException e){
            System.out.println("Project with id: "+projectId+" not found!");
        } catch (Exception e){
            if (entityManager != null && entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            System.out.println("Failed: "+e.getMessage());
        }
        return Optional.ofNullable(project);
    }

    @Override
    public List<Project> findAllProjects() {
        EntityManager entityManager = null;
        List<Project> projects = new ArrayList<>();
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            projects = entityManager.createQuery("select p from Project p", Project.class)
                    .getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e){
            if (entityManager != null && entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            System.out.println("Failed: "+e.getMessage());
        }
        return projects;
    }

    @Override
    public String updateProjectById(Long projectId, Project newProject) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Project oldProject = entityManager.find(Project.class, projectId);
            if (oldProject == null){
                return "Project with id; "+projectId+" not found!";
            }
            oldProject.setTitle(newProject.getTitle());
            entityManager.getTransaction().commit();
            return oldProject.getTitle() +" successfully updated!";
        }catch (Exception e){
            if (entityManager != null && entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            return "Failed: "+e.getMessage();
        }
    }

    @Override
    public String deleteProjectById(Long projectId) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Project project = entityManager.find(Project.class, projectId);
            if (project == null){
                return "Project with id: "+projectId+" not found!";
            }
            entityManager.remove(project);
            entityManager.getTransaction().commit();
            return project.getTitle() +" deleted";
        }catch (Exception e){
            if (entityManager != null && entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            return "Failed: "+e.getMessage();
        }
    }

    @Override
    public String assignProjectToCompany(Long projectId, Long companyId) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Project projectToAssign = entityManager.createQuery("select p from Project p where id =:projectId", Project.class)
                    .setParameter("projectId", projectId)
                    .getSingleResult();

            Company companyToAssign = entityManager.createQuery("select c from Company c where id =:companyId", Company.class)
                    .setParameter("companyId", companyId)
                    .getSingleResult();
            projectToAssign.setCompany(companyToAssign);
            companyToAssign.getProjects().add(projectToAssign);
            entityManager.getTransaction().commit();
            return "successfully assigned";
        } catch (NoResultException e){
            return projectId +" or "+ companyId + " not found!";
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
