package com.arcade.alibou.service.student;

import com.arcade.alibou.domain.Student;
import com.arcade.alibou.domain.dto.StudentDto;
import com.arcade.alibou.domain.dto.StudentResponseDto;
import com.arcade.alibou.mapper.student.StudentMapper;
import com.arcade.alibou.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceImplTest {

    @InjectMocks
    private StudentServiceImpl service;
    @Mock
    private StudentRepository repository;
    @Mock
    private StudentMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void shouldSaveAStudent() {
        //Given
        StudentDto dto = new StudentDto(
                "John",
                "Doe",
                "JohnDoe@proton.me",
                1
        );

        Student student = new Student(
                "John",
                "Doe",
                "JohnDoe@proton.me",
                20);

        Student studentSave = new Student(
                "John",
                "Doe",
                "JohnDoe@proton.me",
                20);
        studentSave.setId(1);

        //Mockito
        Mockito.when(mapper.toStudent(dto)).thenReturn(student);
        Mockito.when(repository.save(student)).thenReturn(studentSave);
        Mockito.when(mapper.toStudentResponseDto(studentSave)).thenReturn(new StudentResponseDto(
                "John",
                "Doe",
                "JohnDoe@proton.me"));


        //When
        StudentResponseDto responseDto = service.createNew(dto);

        //Then
        assertEquals(dto.firstName(), responseDto.fistName());
        assertEquals(dto.lastName(), responseDto.LastName());
        assertEquals(dto.email(), responseDto.email());

    }
}