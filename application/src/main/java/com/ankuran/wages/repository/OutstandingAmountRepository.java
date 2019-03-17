package com.ankuran.wages.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ankuran.wages.model.OutstandingAmountDao;

/**
 * @author Sushil Mittal.
 * @Created At 12/03/19.
 */
@Repository
public interface OutstandingAmountRepository extends JpaRepository<OutstandingAmountDao, Long> {
	public OutstandingAmountDao findByCentreIdAndEmployeeId(Long centreId, Long employeeId);
	public List<OutstandingAmountDao> findAllByCentreId(Long centreId);
}
