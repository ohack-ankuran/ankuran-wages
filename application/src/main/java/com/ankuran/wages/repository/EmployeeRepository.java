package com.ankuran.wages.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ankuran.wages.model.EmployeeDao;

/**
 * @author Sushil Mittal.
 * @Created At 24/02/19.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDao, Long> {
    public EmployeeDao findByCentreIdAndFullName(Long centreId, String fullName);
    public EmployeeDao findByCentreIdAndId(Long centreId, Long id);
	public List<EmployeeDao> findAllByCentreIdOrderByFullNameAsc(Long centreId);
	public List<EmployeeDao> findAllByCentreIdAndStatusNotOrderByFullNameAsc(Long centreId, Byte status);
	public EmployeeDao findByCentreIdAndFullNameAndJoiningTimeBetween(Long centreId, String fullName, Date lowerJoiningTime, Date upperJoiningTime);
}
