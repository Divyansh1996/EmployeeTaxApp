package com.example.employeetax.service;

import com.example.employeetax.model.Employee;
import com.example.employeetax.model.EmployeeResponse;
import com.example.employeetax.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    public void storeEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    public EmployeeResponse getEmployeeInfo(int employeeId){
        Employee employee = employeeRepository.findEmployeeById(employeeId);
        int salaryPerYear = calculateTotalSalary(employee);
        double taxAmount =  calculateTaxAmount(salaryPerYear);
        double cessAmount = calculateCessAmount(taxAmount,salaryPerYear);
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setEmployeeId(employeeId);
        employeeResponse.setFirstName(employee.getFirstName());
        employeeResponse.setLastName(employee.getLastName());
        employeeResponse.setYearlySalary(salaryPerYear);
        employeeResponse.setTaxAmount(taxAmount);
        employeeResponse.setCessAmount(cessAmount);
        return employeeResponse;
    }

    public int calculateTotalSalary(Employee employee){
        int salaryPerMonth = employee.getSalaryPerMonth();
        int dojMonth = Integer.parseInt(employee.getDoj().split("/")[1]);
        int dojDate = Integer.parseInt(employee.getDoj().split("/")[0]);
        HashMap<Integer, Integer> monthMapping = new HashMap<>();
        monthMapping.put(4,1); //April
        monthMapping.put(5,2); //May
        monthMapping.put(6,3); //June
        monthMapping.put(7,4); //July
        monthMapping.put(8,5); //August
        monthMapping.put(9,6); //September
        monthMapping.put(10,7); //October
        monthMapping.put(11,8); //November
        monthMapping.put(12,9); //December
        monthMapping.put(1,10); //January
        monthMapping.put(2,11); // Febuary
        monthMapping.put(3,12); //March
        int numberofMonth = (12 - monthMapping.get(dojMonth))+1;
        int totalSalary = salaryPerMonth * numberofMonth;
        if(dojDate>15){
            totalSalary = totalSalary - (salaryPerMonth/2);
        }
        return totalSalary;
    }

    public double calculateTaxAmount(int salaryPerYear){
        double totalTaxAmount = 0;
        if(salaryPerYear<=250000){
            return totalTaxAmount;
        }
        if(salaryPerYear>250000)
        {
            double amt = 0;
            if((salaryPerYear-250000) > 250000){
                amt = 250000;
            }
            else{
                amt = salaryPerYear - 250000;
            }
            totalTaxAmount =  totalTaxAmount+(0.05*amt);
        }
        if(salaryPerYear>500000){
            double amt = 0;
            if((salaryPerYear - 500000) >500000){
                amt = 500000;
            } else {
                amt = salaryPerYear - 500000;
            }
            totalTaxAmount = totalTaxAmount + (0.1*amt);
        }
        if(salaryPerYear>1000000){
            totalTaxAmount = totalTaxAmount + 0.20 *(salaryPerYear - 1000000);
        }
        return totalTaxAmount;
    }

    public double calculateCessAmount(double totalTaxAmount, int salaryPerYear){
         double cessAmount = 0.03*totalTaxAmount;
         if(salaryPerYear>2500000){
             cessAmount = cessAmount + (salaryPerYear-2500000)*0.02;
         }
         return cessAmount;
    }
}
