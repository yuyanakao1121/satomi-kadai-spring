package com.example.demo2.staff;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    List<Staff> findAll();

    void deleteById(int id);
    Staff findById(int id);
}

