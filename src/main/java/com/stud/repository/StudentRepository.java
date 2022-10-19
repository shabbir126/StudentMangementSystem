package com.stud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stud.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
