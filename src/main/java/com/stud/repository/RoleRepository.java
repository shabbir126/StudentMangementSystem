package com.stud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stud.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
