package com.ankuran.wages.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ankuran.wages.model.GroupWagesActivityDao;

/**
 * @author Sushil Mittal.
 * @Created At 24/02/19.
 */
public interface GroupWagesActivityRepository extends JpaRepository<GroupWagesActivityDao, Long> {
    public GroupWagesActivityDao findByActorEmployeeIdAndCentreId(Long employeeId, Long centerId);
    public GroupWagesActivityDao findByCentreIdAndTimeCreatedBetween(Long centreId, Date lowerTimeCreated, Date upperTimeCreated);
}
