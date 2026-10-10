package com.arcade.alibou.controller;

import com.arcade.alibou.domain.School;
import com.arcade.alibou.domain.Student;
import com.arcade.alibou.domain.dto.StudentDto;
import com.arcade.alibou.domain.dto.StudentResponseDto;
import com.arcade.alibou.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository repository;


    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponseDto createNew(@RequestBody StudentDto studentDto) {
        var student = toStudent(studentDto);
        var saveStudent = repository.save(student);
        return toStudentResponseDto(saveStudent);
    }


    //Mapper --- I put it here coz that fuzzy brain piece of shit did
    private Student toStudent(StudentDto dto) {
        var student = new Student();
        var school = new School();
        school.setId(dto.schoolId());

        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setEmail(dto.email());
        student.setSchool(school);

        return student;
    }

    //Mapper --- I put it here coz that fuzzy brain piece of shit did
    private StudentResponseDto toStudentResponseDto(Student student) {
        return new StudentResponseDto(student.getFirstName(), student.getLastName(), student.getEmail());
    }


    @GetMapping("/")
    public List<Student> allStudents() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Student findStudent(
            @PathVariable(name = "id") Integer id) {
        return repository.findById(id).orElse(null);
    }

    @GetMapping("/search/{name}")
    public List<Student> findByName(@PathVariable(name = "name") String name) {
        return repository.findAllByFirstNameContainingIgnoreCase(name);
    }

    @DeleteMapping("/remove/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeStudent(@PathVariable(name = "id") Integer id) {
        repository.deleteById(id);
    }

}
