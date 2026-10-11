package com.arcade.alibou.service.school;

import com.arcade.alibou.domain.dto.SchoolDto;
import com.arcade.alibou.mapper.school.SchoolMapper;
import com.arcade.alibou.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SchoolServiceImpl implements SchoolService {
    private final SchoolRepository repository;
    private final SchoolMapper schoolMapper;

    @Override
    public SchoolDto creat(SchoolDto schoolDto) {
        var saved = schoolMapper.toSchool(schoolDto);
        repository.save(saved);
        return schoolDto;
    }

    @Override
    public List<SchoolDto> get() {
        return repository.findAll()
                .stream()
                .map(schoolMapper::toSchoolDto)
                .collect(Collectors.toList());
    }
}
