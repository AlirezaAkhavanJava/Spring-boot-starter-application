package com.arcade.alibou.mapper.student;

import com.arcade.alibou.domain.School;
import com.arcade.alibou.domain.Student;
import com.arcade.alibou.domain.dto.StudentDto;
import com.arcade.alibou.domain.dto.StudentResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperImplTest {

    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        studentMapper = new StudentMapperImpl();
    }

    @Test
    public void shouldMapStudentDtoToStudent() {
        StudentDto dto = new StudentDto(
                "John",
                "Doe",
                "JohnDoe@proton.me",
                1
        );

        Student student = studentMapper.toStudent(dto);
        assertEquals(dto.firstName(), student.getFirstName());
        assertEquals(dto.lastName(), student.getLastName());
        assertEquals(dto.email(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(dto.schoolId(), student.getSchool().getId());
    }

    @Test
    public void shouldMapStudentToStudentResponseDto() {
        //Given
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmail("idiotGuy@yahoo.com");
        student.setAge(45);


        //When
        StudentResponseDto dto =
                studentMapper.toStudentResponseDto(student);

        //Then
        assertEquals(student.getFirstName(), dto.fistName());
        assertEquals(student.getLastName(), dto.LastName());
        assertEquals(student.getEmail(), dto.email());

    }

    @Test
    public void shouldMapStudentToStudentDto() {
        //Given
        Student student = new Student("Jane", "Doe", "janesMail@email.com", 46);
        School school = new School();
        school.setId(2);
        student.setSchool(school);
        //When
        StudentDto dto = studentMapper.toStudentDto(student);
        //Then
        assertEquals(student.getFirstName(), dto.firstName());
        assertEquals(student.getLastName(), dto.lastName());
        assertEquals(student.getEmail(), dto.email());

    }


    @Test
    public void ShouldThrowNullPointerExceptionWhenDTOIsNull() {
        var exception = assertThrows(NullPointerException.class, () -> studentMapper.toStudent(null));
        assertEquals("Can not be null", exception.getMessage());
        assertNotEquals("BOB", exception.getMessage());
    }

}