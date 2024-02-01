package org.example.service;

import org.example.entities.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    String saveCompany(Company newCompany);
    String saveCompany(Long addressId, Company newCompany);
    Optional<Company> findCompanyById(Long companyId);
    List<Company> findAllCompanies();
    String updateCompanyById(Long companyId, Company newCompany);
    String deleteCompanyById(Long companyId);
    String assignCompanyToAddress(Long addressId, Long companyId);
}
