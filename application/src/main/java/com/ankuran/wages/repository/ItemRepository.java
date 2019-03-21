package com.ankuran.wages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ankuran.wages.model.CentreDao;

@Repository
public interface ItemRepository extends JpaRepository<CentreDao, Long> {

}
