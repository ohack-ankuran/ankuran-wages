package com.ankuran.wages.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ankuran.wages.model.ItemFactDao;

@Repository
public interface ItemFactRepository extends JpaRepository<ItemFactDao, Long> {

	public List<ItemFactDao> findAllByCentreIdAndItemIdAndTimeCreatedBetweenAndTypeAndReason(Long centreId, Long itemId, Date lowerTimeCreated,Date upperTimeCreated, Long type, Long reason);
	public List<ItemFactDao> findAllByItemIdOrderByTimeCreatedDesc(Long itemId);
}
