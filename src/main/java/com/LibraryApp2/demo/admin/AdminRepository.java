package com.LibraryApp2.demo.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminRegModel,Integer>
{
    Optional<AdminRegModel> findByEmailAndPassword(String email, String password);

    Optional<AdminRegModel> findFirstByAdminName(String adminName);


}
