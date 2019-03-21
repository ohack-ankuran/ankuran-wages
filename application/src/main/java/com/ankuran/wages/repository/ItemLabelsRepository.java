package com.ankuran.wages.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ankuran.wages.model.ItemLabelsDao;

@Repository
public interface ItemLabelsRepository  extends JpaRepository<ItemLabelsDao, Long> {

	public List<ItemLabelsDao> findAllByItemId(Long id);
}
