package com.example.c0823l1_be.repository;

import com.example.c0823l1_be.dto.StaffViewDTO;
import com.example.c0823l1_be.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {

    Staff findByUser_Username(String username);
    <T> T findByUser_Username(String username,Class<T> classType);

}
