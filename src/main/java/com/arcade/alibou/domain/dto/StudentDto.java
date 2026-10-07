package com.arcade.alibou.domain.dto;

public record StudentDto
        (
                String firstName,
                String lastName,
                String email,
                Integer schoolId
        ) {
}
