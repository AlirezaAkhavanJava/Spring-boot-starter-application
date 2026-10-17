package com.arcade.alibou.service.student;

import com.arcade.alibou.domain.Student;
import com.arcade.alibou.domain.dto.StudentDto;
import com.arcade.alibou.domain.dto.StudentResponseDto;
import com.arcade.alibou.mapper.student.StudentMapper;
import com.arcade.alibou.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepository repository;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentServiceImpl service;

    // Shared test data
    private Student createStudent() {
        Student student = new Student(
                "John",
                "Doe",
                "john@example.com",
                20
        );
        student.setId(1);
        return student;
    }
    private StudentDto createStudentDto() {
        return new StudentDto(
                "John",
                "Doe",
                "john@example.com",
                20
        );
    }
    private StudentResponseDto createResponseDto() {
        return new StudentResponseDto(
                "John",
                "Doe",
                "john@example.com"
        );
    }
    @Test
    void shouldSaveAStudent() {
        // Given
        StudentDto dto = createStudentDto();
        Student student = createStudent();
        StudentResponseDto expected = createResponseDto();

        when(studentMapper.toStudent(dto)).thenReturn(student);
        when(repository.save(student)).thenReturn(student);
        when(studentMapper.toStudentResponseDto(student))
                .thenReturn(expected);

        // When
        StudentResponseDto actual = service.createNew(dto);

        // Then
        assertEquals(expected, actual);

        verify(studentMapper).toStudent(dto);
        verify(repository).save(student);
        verify(studentMapper).toStudentResponseDto(student);
    }
    @Test
    void shouldReturnAllStudents() {
        // Given
        Student student1 = createStudent();

        Student student2 = new Student(
                "Jane",
                "Smith",
                "jane@example.com",
                22
        );
        student2.setId(2);

        StudentResponseDto response1 = createResponseDto();

        StudentResponseDto response2 = new StudentResponseDto(
                "Jane",
                "Smith",
                "jane@example.com"
        );

        when(repository.findAll())
                .thenReturn(List.of(student1, student2));

        when(studentMapper.toStudentResponseDto(student1))
                .thenReturn(response1);

        when(studentMapper.toStudentResponseDto(student2))
                .thenReturn(response2);

        // When
        List<StudentResponseDto> actual = service.allStudents();

        // Then
        assertEquals(2, actual.size());
        assertEquals(List.of(response1, response2), actual);

        verify(repository).findAll();
        verify(studentMapper).toStudentResponseDto(student1);
        verify(studentMapper).toStudentResponseDto(student2);
    }

    @Test
    void shouldReturnStudentById() {
        // Given
        Integer id = 1;
        Student student = createStudent();
        StudentDto expected = createStudentDto();

        when(repository.findById(id))
                .thenReturn(Optional.of(student));

        when(studentMapper.toStudentDto(student))
                .thenReturn(expected);

        // When
        StudentDto actual = service.findStudent(id);

        // Then
        assertEquals(expected, actual);

        verify(repository).findById(id);
        verify(studentMapper).toStudentDto(student);
    }

    @Test
    void shouldReturnNullWhenStudentDoesNotExist() {
        // Given
        Integer id = 99;

        when(repository.findById(id))
                .thenReturn(Optional.empty());

        // When
        StudentDto actual = service.findStudent(id);

        // Then
        assertNull(actual);

        verify(repository).findById(id);
        verifyNoInteractions(studentMapper);
    }

    @Test
    void shouldFindStudentsByName() {
        // Given
        String name = "John";

        Student student1 = createStudent();

        Student student2 = new Student(
                "Johnny",
                "Brown",
                "johnny@example.com",
                25
        );
        student2.setId(2);

        StudentDto dto1 = createStudentDto();

        StudentDto dto2 = new StudentDto(
                "Johnny",
                "Brown",
                "johnny@example.com",
                25
        );

        when(repository.findAllByFirstNameContainingIgnoreCase(name))
                .thenReturn(List.of(student1, student2));

        when(studentMapper.toStudentDto(student1)).thenReturn(dto1);
        when(studentMapper.toStudentDto(student2)).thenReturn(dto2);

        // When
        List<StudentDto> actual = service.findByName(name);

        // Then
        assertEquals(2, actual.size());
        assertEquals(List.of(dto1, dto2), actual);

        verify(repository).findAllByFirstNameContainingIgnoreCase(name);
        verify(studentMapper).toStudentDto(student1);
        verify(studentMapper).toStudentDto(student2);
    }

    @Test
    void shouldReturnEmptyListWhenNoStudentsMatchName() {
        // Given
        String name = "Unknown";

        when(repository.findAllByFirstNameContainingIgnoreCase(name))
                .thenReturn(List.of());

        // When
        List<StudentDto> actual = service.findByName(name);

        // Then
        assertNotNull(actual);
        assertTrue(actual.isEmpty());

        verify(repository).findAllByFirstNameContainingIgnoreCase(name);
        verifyNoInteractions(studentMapper);
    }

    @Test
    void shouldRemoveStudentById() {
        // Given
        Integer id = 1;

        // When
        service.removeStudent(id);

        // Then
        verify(repository).deleteById(id);
        verifyNoInteractions(studentMapper);
    }

    @Test
    void shouldHandleSqlInjectionPayloadInStudentName() {
        // Given
        String maliciousName = "' OR '1'='1' --";

        when(repository.findAllByFirstNameContainingIgnoreCase(maliciousName))
                .thenReturn(List.of());

        // When
        List<StudentDto> result = service.findByName(maliciousName);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(repository)
                .findAllByFirstNameContainingIgnoreCase(maliciousName);
        verifyNoInteractions(studentMapper);
    }

    @Test
    void shouldReturnAllStudentsUsingAny() {
        // Given
        Student student1 = new Student(
                "Alice", "Smith", "alice@example.com", 20
        );

        Student student2 = new Student(
                "Bob", "Jones", "bob@example.com", 25
        );

        StudentResponseDto response1 = new StudentResponseDto(
                "Alice", "Smith", "alice@example.com"
        );

        StudentResponseDto response2 = new StudentResponseDto(
                "Bob", "Jones", "bob@example.com"
        );

        when(repository.findAll())
                .thenReturn(List.of(student1, student2));

        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(response1, response2);

        // When
        List<StudentResponseDto> result = service.allStudents();

        // Then
        assertEquals(2, result.size());
        assertEquals(List.of(response1, response2), result);

        verify(repository).findAll();
        verify(studentMapper, times(2))
                .toStudentResponseDto(any(Student.class));
    }

}
