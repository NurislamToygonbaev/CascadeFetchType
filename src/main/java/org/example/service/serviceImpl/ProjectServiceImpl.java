package org.example.service.serviceImpl;

import org.example.dao.ProjectDao;
import org.example.dao.daoImpl.ProjectDaoImpl;
import org.example.entities.Project;
import org.example.service.ProjectService;

import java.util.List;
import java.util.Optional;

public class ProjectServiceImpl implements ProjectService {
    ProjectDao projectDao = new ProjectDaoImpl();
    @Override
    public String saveProject(Project newProject) {
        return projectDao.saveProject(newProject);
    }

    @Override
    public String saveProject(Long companyId, Project newProject) {
        return projectDao.saveProject(companyId, newProject);
    }

    @Override
    public Optional<Project> findProjectById(Long projectId) {
        return projectDao.findProjectById(projectId);
    }

    @Override
    public List<Project> findAllProjects() {
        return projectDao.findAllProjects();
    }

    @Override
    public String updateProjectById(Long projectId, Project newProject) {
        return projectDao.updateProjectById(projectId, newProject);
    }

    @Override
    public String deleteProjectById(Long projectId) {
        return projectDao.deleteProjectById(projectId);
    }

    @Override
    public String assignProjectToCompany(Long projectId, Long companyId) {
        return projectDao.assignProjectToCompany(projectId, companyId);
    }
}
