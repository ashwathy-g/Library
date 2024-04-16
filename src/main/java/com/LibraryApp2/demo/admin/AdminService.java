package com.LibraryApp2.demo.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Service
public class AdminService
{
    @Autowired
    AdminRepository adminRepository;

    public AdminRegModel registerAdmin(String adminName,String password,String email)
    {
        if (adminName==null && password==null)
        {
            return null;
        }
        else {
            if (adminRepository.findFirstByAdminName(adminName).isPresent())
            {
                System.out.println("Duplicate login");
                return null;
            }
            AdminRegModel adminRegModel=new AdminRegModel();
            adminRegModel.setAdminName(adminName);
            adminRegModel.setEmail(email);
            adminRegModel.setPassword(password);
            adminRegModel.setRole("ADMIN");
            return adminRepository.save(adminRegModel);
        }
    }
    public AdminRegModel authenticate(String email,String password)
    {
        return adminRepository.findByEmailAndPassword(email,password).orElse(null);
    }


}
