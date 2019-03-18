package com.ankuran.wages.repository;

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
    public EmployeeDao findByCentreIdAndAndFullName(Long centreId, String fullName);
    public EmployeeDao findByCentreIdAndId(Long centreId, Long id);
	public List<EmployeeDao> findAllByCentreId(Long centreId);
	public List<EmployeeDao> findAllByCentreIdAndStatusNot(Long centreId, Byte status);
}
