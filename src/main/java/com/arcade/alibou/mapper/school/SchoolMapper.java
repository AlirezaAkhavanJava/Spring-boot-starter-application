package com.arcade.alibou.mapper.school;

import com.arcade.alibou.domain.School;
import com.arcade.alibou.domain.dto.SchoolDto;

public interface SchoolMapper {
    School toSchool(SchoolDto dto);
    SchoolDto toSchoolDto(School school);
}
