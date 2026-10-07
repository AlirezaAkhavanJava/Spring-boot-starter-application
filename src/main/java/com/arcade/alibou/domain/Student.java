package com.arcade.alibou.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String firstName;

    @Column(length = 120)
    private String lastName;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "age")
    private int age;

    @OneToOne(mappedBy = "student" , cascade = CascadeType.ALL)
    private StudentProfile studentProfile;

    @ManyToOne
    @JoinColumn(name = "schoolId")
    @JsonBackReference
    private School school;

}
