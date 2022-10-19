package com.stud.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stud.model.Student;
import com.stud.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repository;
	
	public List<Student> getAllStudents()
	{
		return repository.findAll();
	}
	
	public void Save(Student student) {
		if (Objects.nonNull(student))
		{
			repository.save(student);
		}
	}
	
	public Optional<Student> getStudentId(int id)
	{
		return repository.findById(id);
	}
	
	public void removeById(int id)
	{
		 repository.deleteById(id);
	}
	
}
