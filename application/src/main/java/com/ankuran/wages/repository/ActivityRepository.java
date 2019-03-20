package com.ankuran.wages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ankuran.wages.model.EmployeeDao;

@Repository
public interface ActivityRepository extends JpaRepository<EmployeeDao, Long> {

}
