package com.example.employeetax.controller;

import com.example.employeetax.model.Employee;
import com.example.employeetax.model.EmployeeResponse;
import com.example.employeetax.model.StoreEmployeeResponse;
import com.example.employeetax.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/storeEmployee")
    public ResponseEntity<StoreEmployeeResponse> storeEmployee(@RequestBody @Valid Employee employee){
        employeeService.storeEmployee(employee);
        StoreEmployeeResponse storeEmployeeResponse = new StoreEmployeeResponse();
        storeEmployeeResponse.setMessage("Employee info got saved");
        return ResponseEntity.ok(storeEmployeeResponse);
    }

    @GetMapping("/getEmployeeInfo")
    public ResponseEntity<EmployeeResponse> getEmployeeInfo(@RequestParam("employeeId") int employeeId){
        EmployeeResponse employeeInfo = employeeService.getEmployeeInfo(employeeId);
        return ResponseEntity.ok(employeeInfo);
    }
}
