package com.example.employeetax.model;

import lombok.Data;

@Data
public class EmployeeResponse {
    private int employeeId;
    private String firstName;
    private String lastName;
    private int yearlySalary;
    private double taxAmount;
    private double cessAmount;

}
