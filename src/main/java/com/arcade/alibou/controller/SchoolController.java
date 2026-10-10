package com.arcade.alibou.controller;

import com.arcade.alibou.domain.School;
import com.arcade.alibou.domain.dto.SchoolDto;
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

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SchoolDto creat(@RequestBody SchoolDto schoolDto) {
        var saved = toSchool(schoolDto);
        repository.save(saved);
        return schoolDto;
    }


    private School toSchool(SchoolDto dto) {
        return new School(dto.name());
    }

    private SchoolDto toSchoolDto(School school) {
        return new SchoolDto(school.getName());
    }

    @GetMapping("")
    public List<SchoolDto> get() {
        return repository.findAll()
                .stream()
                .map(this::toSchoolDto)
                .collect(Collectors.toList());
    }
}
