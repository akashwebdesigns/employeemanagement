package com.springbootprojects.employeemanagement.repositories;

import com.springbootprojects.employeemanagement.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Integer> {

}
