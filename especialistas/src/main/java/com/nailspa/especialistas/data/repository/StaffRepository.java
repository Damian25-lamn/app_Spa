package com.nailspa.especialistas.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nailspa.especialistas.data.entity.Staff;

import java.util.List;

public interface StaffRepository extends MongoRepository<Staff, String> {
    List<Staff> findByRol(String rol);
}
