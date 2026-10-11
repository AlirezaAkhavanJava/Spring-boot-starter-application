package com.arcade.alibou.controller;

import com.arcade.alibou.domain.dto.SchoolDto;
import com.arcade.alibou.service.school.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/schools")
public class SchoolController {

    private final SchoolService service;


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SchoolDto creat(@RequestBody SchoolDto schoolDto) {
        return service.creat(schoolDto);
    }


    @GetMapping("")
    public List<SchoolDto> get() {
        return service.get();
    }
}
