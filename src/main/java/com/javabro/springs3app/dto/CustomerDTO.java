package com.javabro.springs3app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerDTO {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Integer age;
    private BigDecimal salary;
}
