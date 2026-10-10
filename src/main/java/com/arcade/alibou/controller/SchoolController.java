package com.arcade.alibou.controller;

import com.arcade.alibou.domain.dto.SchoolDto;
import com.arcade.alibou.mapper.school.SchoolMapper;
import com.arcade.alibou.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/schools")
public class SchoolController {


    private final SchoolRepository repository;
    private final SchoolMapper schoolMapper;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SchoolDto creat(@RequestBody SchoolDto schoolDto) {
        var saved = schoolMapper.toSchool(schoolDto);
        repository.save(saved);
        return schoolDto;
    }


    @GetMapping("")
    public List<SchoolDto> get() {
        return repository.findAll()
                .stream()
                .map(schoolMapper::toSchoolDto)
                .collect(Collectors.toList());
    }
}
