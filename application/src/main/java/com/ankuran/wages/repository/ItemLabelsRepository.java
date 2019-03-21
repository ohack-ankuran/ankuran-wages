package com.ankuran.wages.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemLabelsRepository  extends JpaRepository<ItemLabelsRepository, Long> {

}
