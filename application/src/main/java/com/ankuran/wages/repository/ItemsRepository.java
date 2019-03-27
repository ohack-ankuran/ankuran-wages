package com.ankuran.wages.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ankuran.wages.model.ItemsDao;

@Repository
public interface ItemsRepository extends JpaRepository<ItemsDao, Long> {
	public List<ItemsDao> findAllByTimeCreatedBetween(Date lowerTimeCreated,Date upperTimeCreated);
	public List<ItemsDao> findAllByCategory(String category);
}
