package com.arcade.alibou.service.student;

import com.arcade.alibou.mapper.student.StudentMapper;
import com.arcade.alibou.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceImplTest {

    @InjectMocks
    private StudentService service;
    @Mock
    private StudentRepository repository;
    @Mock
    private StudentMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
}