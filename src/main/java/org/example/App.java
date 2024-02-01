package org.example;

import org.example.entities.Address;
import org.example.entities.Company;
import org.example.entities.Programmer;
import org.example.entities.Project;
import org.example.service.AddressService;
import org.example.service.CompanyService;
import org.example.service.ProgrammerService;
import org.example.service.ProjectService;
import org.example.service.serviceImpl.AddressServiceImpl;
import org.example.service.serviceImpl.CompanyServiceImpl;
import org.example.service.serviceImpl.ProgrammerServiceImpl;
import org.example.service.serviceImpl.ProjectServiceImpl;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
//        AddressService addressService = new AddressServiceImpl();
//        System.out.println(addressService.saveAddress(new Address("Tokio")));

//        System.out.println(addressService.findAddressById(5L));

//        System.out.println(addressService.getAllAddresses());


//        System.out.println(addressService.updateAddressById(1L, new Address("USA")));

//        System.out.println(addressService.deleteAddressById(3L));


        CompanyService companyService = new CompanyServiceImpl();
//        System.out.println(companyService.saveCompany(new Company("Microsoft")));
//        System.out.println(companyService.saveCompany(2L, new Company("Lenovo")));

//        System.out.println(companyService.findCompanyById(2L));

//        System.out.println(companyService.findAllCompanies());

//        System.out.println(companyService.updateCompanyById(2L, new Company("Amazon")));

//        System.out.println(companyService.assignCompanyToAddress(3L, 1L));

        System.out.println(companyService.deleteCompanyById(1L));

//        ProgrammerService service = new ProgrammerServiceImpl();
//        System.out.println(service.saveProgrammer(8L, new Programmer("nur toy", "nur@gmail.com")));

//        System.out.println(service.findProgrammerById(1L));

//        System.out.println(service.findAllProgrammers());


//        System.out.println(service.updateProgrammerById(1L, new Programmer("Nurkamil Kamchiev", "Nurkamil@gmail.com")));

//        System.out.println(service.assignProgrammerToProject(3L, 1L));

//        System.out.println(service.deleteProgrammerById(3L));


//        ProjectService projectService = new ProjectServiceImpl();
//        System.out.println(projectService.saveProject(1L, new Project("tesla")));


//        System.out.println(projectService.findProjectById(1L));

//        System.out.println(projectService.findAllProjects());

//        System.out.println(projectService.updateProjectById(3L, new Project("LMS")));


//        System.out.println(projectService.assignProjectToCompany(2L, 1L));


//        System.out.println(projectService.deleteProjectById(4L));


    }


}
