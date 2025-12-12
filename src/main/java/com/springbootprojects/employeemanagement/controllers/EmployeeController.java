package com.springbootprojects.employeemanagement.controllers;


import com.springbootprojects.employeemanagement.dto.EmployeeDTO;
import com.springbootprojects.employeemanagement.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
public class EmployeeController {

    final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Integer empId){
        return employeeService.getEmloyeeById(empId);
    }

    //It will work here, not in services
//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<String> employeeNotFoundHandler(NoSuchElementException exception){
//        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
//    }

    @GetMapping("/employee")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    //Create Employee
    @PostMapping("/employee")  //@Valid annotation will validate the EmployeeDTO first before going to the service layer. It will also validate if there is any nested object
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @Valid EmployeeDTO emp){

        //More specific status code creation
        return new ResponseEntity<>(employeeService.createEmployee(emp), HttpStatus.CREATED) ;
    }

    //Update entire employee
    @PutMapping("/employee/{empId}")
    public ResponseEntity<EmployeeDTO> updateEntireEmployeeById(@RequestBody EmployeeDTO employee,@PathVariable Integer empId){
        return ResponseEntity.ok(employeeService.updateEntireEmployeeById(employee,empId));
    }

    //Update partial employee
    @PatchMapping("/employee/{empId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployee(@RequestBody Map<String,Object> updates, @PathVariable Integer empId){
        EmployeeDTO employee = employeeService.updatePartialEmployee(updates,empId);
        if(employee!=null){
            return ResponseEntity.ok(employee);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    //Delete Employee
    @DeleteMapping("/employee/{empId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Integer empId){
        Boolean isDeleted = employeeService.deleteEmployeeById(empId);
        return isDeleted?ResponseEntity.ok(isDeleted):new ResponseEntity<>(isDeleted,HttpStatus.NOT_FOUND);
    }
}
