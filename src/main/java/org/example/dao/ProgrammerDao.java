package org.example.dao;

import org.example.entities.Programmer;

import java.util.List;
import java.util.Optional;

public interface ProgrammerDao {
    String saveProgrammer(Long addressId, Programmer newProgrammer);
    Optional<Programmer> findProgrammerById(Long programmerId);
    List<Programmer> findAllProgrammers();
    String updateProgrammerById(Long programmerId, Programmer newProgrammer);
    String deleteProgrammerById(Long programmerId);
    String assignProgrammerToProject(Long programmerId, Long projectId);
}
