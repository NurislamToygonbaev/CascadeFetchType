package org.example.service.serviceImpl;

import org.example.dao.CompanyDao;
import org.example.dao.daoImpl.CompanyDaoImpl;
import org.example.entities.Company;
import org.example.service.CompanyService;

import java.util.List;
import java.util.Optional;

public class CompanyServiceImpl implements CompanyService {
    private final CompanyDao companyDao = new CompanyDaoImpl();
    @Override
    public String saveCompany(Company newCompany) {
        return companyDao.saveCompany(newCompany);
    }

    @Override
    public String saveCompany(Long addressId, Company newCompany) {
        return companyDao.saveCompany(addressId, newCompany);
    }

    @Override
    public Optional<Company> findCompanyById(Long companyId) {
        return companyDao.findCompanyById(companyId);
    }

    @Override
    public List<Company> findAllCompanies() {
        return companyDao.findAllCompanies();
    }

    @Override
    public String updateCompanyById(Long companyId, Company newCompany) {
        return companyDao.updateCompanyById(companyId, newCompany);
    }

    @Override
    public String deleteCompanyById(Long companyId) {
        return companyDao.deleteCompanyById(companyId);
    }

    @Override
    public String assignCompanyToAddress(Long addressId, Long companyId) {
        return companyDao.assignCompanyToAddress(addressId, companyId);
    }
}
