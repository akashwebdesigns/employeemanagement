package com.springbootprojects.employeemanagement.controllers;

import com.springbootprojects.employeemanagement.dto.DepartmentDTO;
import com.springbootprojects.employeemanagement.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    //Get all departments
    @GetMapping("/departments")
    public ResponseEntity<List<DepartmentDTO>> getDepartments(){
        return new ResponseEntity<>(departmentService.getAllDepartments(),HttpStatus.OK);
    }


    //Get department by id
    @GetMapping("/departments/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Integer id){
        return new ResponseEntity<>(departmentService.getDepartmentById(id),HttpStatus.OK);
    }

    //Create Department
    @PostMapping("/departments")
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody @Valid DepartmentDTO department){
        return new ResponseEntity<>(departmentService.createDepartment(department),HttpStatus.CREATED);
    }
//
    //Update entire department
    @PutMapping("/departments/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@RequestBody DepartmentDTO department,@PathVariable Integer id){
        return new ResponseEntity<>(departmentService.updateDepartment(department,id),HttpStatus.OK);
    }

    //Delete department
    @DeleteMapping("/departments/{id}")
    public ResponseEntity<Boolean> deleteDepartment(@PathVariable Integer id){
        Boolean isDeleted = departmentService.deleteDepartmentById(id);
        return isDeleted?ResponseEntity.ok(isDeleted):new ResponseEntity<>(isDeleted,HttpStatus.NOT_FOUND);
    }
}
