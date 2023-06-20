package com.example.demo2.staff;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    List<Staff> findAll();//データベース内のすべてのStaffエンティティを取得します。

    void deleteById(int id);//指定されたIDに基づいてStaffエンティティを削除します。
    Staff findById(int id);//指定されたIDに基づいてStaffエンティティを検索します。
}

