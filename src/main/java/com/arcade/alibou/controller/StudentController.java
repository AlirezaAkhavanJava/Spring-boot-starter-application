package com.arcade.alibou.controller;

import com.arcade.alibou.domain.dto.StudentDto;
import com.arcade.alibou.domain.dto.StudentResponseDto;
import com.arcade.alibou.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;


    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponseDto createNew(@RequestBody StudentDto studentDto) {
        return service.createNew(studentDto);
    }


    @GetMapping("/")
    public List<StudentResponseDto> allStudents() {
        return service.allStudents();
    }

    @GetMapping("/{id}")
    public StudentDto findStudent(
            @PathVariable(name = "id") Integer id) {
        return service.findStudent(id);
    }

    @GetMapping("/search/{name}")
    public List<StudentDto> findByName(@PathVariable(name = "name") String name) {
        return service.findByName(name);
    }

    @DeleteMapping("/remove/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeStudent(@PathVariable(name = "id") Integer id) {
        service.removeStudent(id);
    }

}
