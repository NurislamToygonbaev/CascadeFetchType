package org.example.service.serviceImpl;

import org.example.dao.ProgrammerDao;
import org.example.dao.daoImpl.ProgrammerDaoImpl;
import org.example.entities.Programmer;
import org.example.service.ProgrammerService;

import java.util.List;
import java.util.Optional;

public class ProgrammerServiceImpl implements ProgrammerService {
    private final ProgrammerDao programmerDao = new ProgrammerDaoImpl();
    @Override
    public String saveProgrammer(Long addressId, Programmer newProgrammer) {
        return programmerDao.saveProgrammer(addressId, newProgrammer);
    }

    @Override
    public Optional<Programmer> findProgrammerById(Long programmerId) {
        return programmerDao.findProgrammerById(programmerId);
    }

    @Override
    public List<Programmer> findAllProgrammers() {
        return programmerDao.findAllProgrammers();
    }

    @Override
    public String updateProgrammerById(Long programmerId, Programmer newProgrammer) {
        return programmerDao.updateProgrammerById(programmerId, newProgrammer);
    }

    @Override
    public String deleteProgrammerById(Long programmerId) {
        return programmerDao.deleteProgrammerById(programmerId);
    }

    @Override
    public String assignProgrammerToProject(Long programmerId, Long projectId) {
        return programmerDao.assignProgrammerToProject(programmerId, projectId);
    }
}
