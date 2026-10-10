package com.arcade.alibou.service;

import com.arcade.alibou.domain.dto.StudentDto;
import com.arcade.alibou.domain.dto.StudentResponseDto;

import java.util.List;

public interface StudentService {
    StudentResponseDto createNew(StudentDto studentDto);

    List<StudentResponseDto> allStudents();

    StudentDto findStudent(Integer id);

    List<StudentDto> findByName(String name);

    void removeStudent(Integer id);
}
