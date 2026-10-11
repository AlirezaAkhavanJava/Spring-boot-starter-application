package com.arcade.alibou.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public record StudentDto
        (
                @NotEmpty(message = "Can not be blank")
                @Length(min = 3, max = 100)
                String firstName,
                @NotEmpty
                String lastName,
                @NotEmpty
                @Email
                String email,
                Integer schoolId
        ) {
}
