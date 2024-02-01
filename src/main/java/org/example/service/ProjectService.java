package org.example.service;

import org.example.entities.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    String saveProject(Project newProject);
    String saveProject(Long companyId, Project newProject);
    Optional<Project> findProjectById(Long projectId);
    List<Project> findAllProjects();
    String updateProjectById(Long projectId, Project newProject);
    String deleteProjectById(Long projectId);
    String assignProjectToCompany(Long projectId, Long companyId);
}
