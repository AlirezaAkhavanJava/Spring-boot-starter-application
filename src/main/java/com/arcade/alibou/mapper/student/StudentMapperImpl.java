package com.arcade.alibou.mapper.student;

import com.arcade.alibou.domain.School;
import com.arcade.alibou.domain.Student;
import com.arcade.alibou.domain.dto.StudentDto;
import com.arcade.alibou.domain.dto.StudentResponseDto;
import org.springframework.stereotype.Component;

@Component
public class StudentMapperImpl implements StudentMapper {
    public Student toStudent(StudentDto dto) {
        var student = new Student();
        var school = new School();
        school.setId(dto.schoolId());

        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setEmail(dto.email());
        student.setSchool(school);

        return student;
    }

    public StudentResponseDto toStudentResponseDto(Student student) {
        return new StudentResponseDto(student.getFirstName(), student.getLastName(), student.getEmail());
    }

    public StudentDto toStudentDto(Student student) {
        return new StudentDto(student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getSchool().getId());
    }
}
