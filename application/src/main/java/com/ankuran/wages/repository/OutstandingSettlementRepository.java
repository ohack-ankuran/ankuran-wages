package com.ankuran.wages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ankuran.wages.model.OutstandingSettlementDao;

@Repository
public interface OutstandingSettlementRepository extends JpaRepository<OutstandingSettlementDao, Long> {
}
