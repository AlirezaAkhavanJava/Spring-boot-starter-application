package com.arcade.alibou.mapper.school;

import com.arcade.alibou.domain.School;
import com.arcade.alibou.domain.dto.SchoolDto;
import org.springframework.stereotype.Component;

@Component
public class SchoolMapperImpl implements SchoolMapper {
    public School toSchool(SchoolDto dto) {
        return new School(dto.name());
    }

    public SchoolDto toSchoolDto(School school) {
        return new SchoolDto(school.getName());
    }
}
