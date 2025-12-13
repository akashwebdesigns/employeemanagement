package com.springbootprojects.employeemanagement.services;

import com.springbootprojects.employeemanagement.customExceptions.ResourceNotFoundException;
import com.springbootprojects.employeemanagement.dto.DepartmentDTO;
import com.springbootprojects.employeemanagement.entities.DepartmentEntity;
import com.springbootprojects.employeemanagement.repositories.DepartmentRepository;
import jakarta.persistence.NoResultException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    final DepartmentRepository departmentRepository;
    final ModelMapper mapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper mapper) {
        this.departmentRepository = departmentRepository;
        this.mapper = mapper;
    }


    //Get All Departments
    public List<DepartmentDTO> getAllDepartments(){
        List<DepartmentEntity> depts = departmentRepository.findAll();
        if(depts.isEmpty()){
            throw new ResourceNotFoundException("No Department Found");
        }
        return depts.stream().map((dept)->mapper.map(dept,DepartmentDTO.class)).collect(Collectors.toList());
    }

    //Get department by id
    public DepartmentDTO getDepartmentById(Integer id){
        DepartmentEntity department = departmentRepository.findById(id).orElseThrow(()->{
            return new ResourceNotFoundException("Department Not Found");
        });
        return mapper.map(department,DepartmentDTO.class);
    }

    //Create Department
    public DepartmentDTO createDepartment(DepartmentDTO department){
        DepartmentEntity dept = mapper.map(department,DepartmentEntity.class);
        DepartmentEntity savedDept = departmentRepository.save(dept);
        return mapper.map(savedDept, DepartmentDTO.class);

    }


    //Update Entire Department
    public DepartmentDTO updateDepartment(DepartmentDTO department,Integer id){
        DepartmentEntity dept = departmentRepository.findById(id).orElseThrow(()->{
            return new NoResultException("No Department Found");
        });

        dept.setId(id);
        return mapper.map(departmentRepository.save(mapper.map(department,DepartmentEntity.class)), DepartmentDTO.class);
    }

    //Delete Department
    public Boolean deleteDepartmentById(Integer id){
        Optional<DepartmentEntity> dept = departmentRepository.findById(id);
        if(!dept.isEmpty()){
             departmentRepository.deleteById(id);
             return true;
        }
        return false;
    }
}
