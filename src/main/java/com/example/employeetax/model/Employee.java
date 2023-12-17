package com.example.employeetax.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
public class Employee {
    @Id
    @NonNull
    private int employeeId;
    @NotBlank(message = "FirstName cannot be blank")
    private String firstName;
    @NotBlank(message = "LastName cannot be blank")
    private String lastName;
    @Email(message = "Email is not valid")
    private String email;
    @NotNull
    private int phoneNumber;
    @NotBlank(message = "DOJ cannot be blank")
    private String doj;
    @NonNull
    private int salaryPerMonth;
}
