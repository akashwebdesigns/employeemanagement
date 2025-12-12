package com.springbootprojects.employeemanagement.services;

import com.springbootprojects.employeemanagement.customExceptions.ResourceNotFoundException;
import com.springbootprojects.employeemanagement.dto.EmployeeDTO;
import com.springbootprojects.employeemanagement.entities.EmployeeEntity;
import com.springbootprojects.employeemanagement.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    final EmployeeRepository employeeRepository;
    final ModelMapper mapper;

    public EmployeeService(EmployeeRepository employeeRepository,ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }


    //Get employee by id
    public EmployeeDTO getEmloyeeById(Integer empId){
        EmployeeEntity employee = employeeRepository
                .findById(empId)
                .orElseThrow(()->new ResourceNotFoundException("No Such employee found"));//orElseThrow expects a supplier which is a functional interface thus we provided a lambda

        return mapper.map(employee,EmployeeDTO.class);
    }


    //This exception handler will not work here because @ExceptionHandler works in Controller(where @RestController is defined) not in service.
    //It will also work in @RestControllerAdvice i.e. Global Exception Handler.

//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<String> employeeNotFoundHandler(NoSuchElementException exception){
//        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
//    }

    //Get all employees
    public List<EmployeeDTO> getAllEmployees(){
        List<EmployeeEntity> employees = employeeRepository.findAll();
        //Converted list of EmployeeEntity to list of EmployeeDTO
        return employees.stream().map(emp->mapper.map(emp,EmployeeDTO.class)).collect(Collectors.toList());
    }

    //Create Employee

    public EmployeeDTO createEmployee(EmployeeDTO emp){
//        ModelMapper modelMapper = new ModelMapper();
        //ModelMapper converts DTO->Entity and vice-versa
        EmployeeEntity toBeSavedEmployee = mapper.map(emp, EmployeeEntity.class);
        EmployeeEntity employee = employeeRepository.save(toBeSavedEmployee);
        return mapper.map(employee,EmployeeDTO.class);
    }


    //Update entire employee data
    public EmployeeDTO updateEntireEmployeeById(EmployeeDTO employee, Integer empId) {

        EmployeeEntity toBeUpdatedEmployee = mapper.map(employee, EmployeeEntity.class);
        toBeUpdatedEmployee.setId(empId);
        return mapper.map(employeeRepository.save(toBeUpdatedEmployee), EmployeeDTO.class);

    }

    //Update partial employee data(!Important)
    public EmployeeDTO updatePartialEmployee(Map<String, Object> updates, Integer empId) {
        Optional<EmployeeEntity> employee = employeeRepository.findById(empId);
        if(employee.isPresent()){
            updates.forEach((key,value)->{
                //We will first fetch the fields which need to be updated from the employee entity class
                Field field = ReflectionUtils.findField(EmployeeEntity.class,key);
                //Since the fields are private in employee entity, we will first make them accessible
                field.setAccessible(true);
                ReflectionUtils.setField(field,employee.get(),value);
            });
            return mapper.map(employeeRepository.save(employee.get()), EmployeeDTO.class);
        }
        return null;

    }


    //Delete Employee

    public Boolean deleteEmployeeById(Integer empId) {
        Optional<EmployeeEntity> employee = employeeRepository.findById(empId);
        if(employee.isPresent()){
            employeeRepository.deleteById(empId);
            return true;
        }
        return false;
    }

}
