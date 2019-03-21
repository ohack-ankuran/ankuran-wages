package com.ankuran.wages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ankuran.wages.model.ItemsDao;

@Repository
public interface ItemsRepository extends JpaRepository<ItemsDao, Long> {

}
