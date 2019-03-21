package com.ankuran.wages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ankuran.wages.model.ItemLabelsDao;

@Repository
public interface ItemLabelsRepository  extends JpaRepository<ItemLabelsDao, Long> {

}
