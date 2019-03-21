package com.ankuran.wages.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ankuran.wages.model.CentreDao;

/**
 * @author Sushil Mittal.
 * @Created At 24/02/19.
 */
@Repository
public interface CenterRepository extends JpaRepository<CentreDao, Long> {

    public CentreDao findByName(String name);
    public CentreDao findById(long id);
    public List<CentreDao> findAll();
}
