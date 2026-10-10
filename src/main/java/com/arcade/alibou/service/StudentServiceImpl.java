package com.arcade.alibou.service;

import com.arcade.alibou.domain.dto.StudentDto;
import com.arcade.alibou.domain.dto.StudentResponseDto;
import com.arcade.alibou.mapper.student.StudentMapper;
import com.arcade.alibou.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository repository;
    private final StudentMapper studentMapper;

    @Override
    public StudentResponseDto createNew(StudentDto studentDto) {
        var student = studentMapper.toStudent(studentDto);
        var saveStudent = repository.save(student);
        return studentMapper.toStudentResponseDto(saveStudent);
    }

    @Override
    public List<StudentResponseDto> allStudents() {
        return repository.findAll().stream()
                .map(studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto findStudent(Integer id) {
        return repository.findById(id).map(studentMapper::toStudentDto).orElse(null);
    }

    @Override
    public List<StudentDto> findByName(String name) {
        return repository
                .findAllByFirstNameContainingIgnoreCase(name)
                .stream()
                .map(studentMapper::toStudentDto)
                .collect(Collectors.toList());
    }

    @Override
    public void removeStudent(Integer id) {
        repository.deleteById(id);
    }
}
