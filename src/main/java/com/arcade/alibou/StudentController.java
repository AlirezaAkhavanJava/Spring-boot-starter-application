package com.arcade.alibou;

import com.arcade.alibou.Domain.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentRepository repository;


    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createNew(@RequestBody Student student) {
        return repository.save(student);
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
