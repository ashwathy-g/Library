package com.LibraryApp2.demo.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path="/admin")
public class AdminController
{
    @Autowired
    AdminService adminService;
    @PostMapping(path="/AdminReg")
    public Boolean register(@RequestBody AdminRegModel adminRegModel)
    {
        AdminRegModel registeredAdmin=adminService.registerAdmin(adminRegModel.getAdminName(),adminRegModel.getPassword(),adminRegModel.getEmail());
        return registeredAdmin==null;
    }

    @PostMapping(path = "/AdminLogin")
    public ResponseEntity<String> login(@RequestBody AdminRegModel adminRegModel) {
        AdminRegModel authenticated = adminService.authenticate(adminRegModel.getEmail(), adminRegModel.getPassword());
        if (authenticated != null) {
            return ResponseEntity.ok("Login Successfully");
     } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");

        }
    }

}
