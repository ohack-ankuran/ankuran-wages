package com.ankuran.wages.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ankuran.wages.model.SettlementDao;

@Repository
public interface SettlementRepository extends JpaRepository<SettlementDao, Long> {

	public List<SettlementDao> findAllByCentreIdAndTimeCreatedBetweenOrderByTimeCreatedDesc(Long centreId,Date lowerTimeCreated,Date upperTimeCreated);
}
