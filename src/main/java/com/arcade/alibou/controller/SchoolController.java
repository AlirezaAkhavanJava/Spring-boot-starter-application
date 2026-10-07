package com.arcade.alibou.controller;

import com.arcade.alibou.domain.School;
import com.arcade.alibou.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/schools")
public class SchoolController {


    private final SchoolRepository repository;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public School creat(@RequestBody School school) {
        return repository.save(school);
    }


    @GetMapping("")
    public List<School> get(){
        return repository.findAll();
    }
}
