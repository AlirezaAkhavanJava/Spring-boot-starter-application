package com.arcade.alibou.mapper.student;

import com.arcade.alibou.domain.Student;
import com.arcade.alibou.domain.dto.StudentDto;
import com.arcade.alibou.domain.dto.StudentResponseDto;

import java.util.List;

public interface StudentMapper {
    Student toStudent(StudentDto dto);
    StudentResponseDto toStudentResponseDto(Student student);
    public StudentDto toStudentDto(Student student);
}
