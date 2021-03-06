package com.ankuran.wages.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ankuran.wages.model.WagesActivityDao;

/**
 * @author Sushil Mittal.
 * @Created At 12/03/19.
 */
public interface WagesActivityRepository extends JpaRepository<WagesActivityDao, Long> {
	public List<WagesActivityDao> findByCentreIdAndEmployeeIdAndTimeCreatedBetween(Long centreId, Long employeeId, Date lowerTimeCreated, Date upperTimeCreated);
	
	public List<WagesActivityDao> findByCentreIdAndEmployeeIdAndTimeCreatedBetweenAndTypeInOrderByTimeCreatedDesc(Long centreId, Long employeeId, Date lowerTimeCreated, Date upperTimeCreated, List<Long> types);
	
	public List<WagesActivityDao> findByCentreIdAndTimeCreatedBetweenAndTypeInOrderByTimeCreatedDesc(Long centreId, Date lowerTimeCreated, Date upperTimeCreated, List<Long> types);
}
