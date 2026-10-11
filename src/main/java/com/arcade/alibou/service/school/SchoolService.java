package com.arcade.alibou.service.school;

import com.arcade.alibou.domain.dto.SchoolDto;

import java.util.List;

public interface SchoolService {
    SchoolDto creat(SchoolDto schoolDto);
    List<SchoolDto> get();
}
