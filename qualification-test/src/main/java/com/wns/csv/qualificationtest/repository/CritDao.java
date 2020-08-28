package com.wns.csv.qualificationtest.repository;

import com.wns.csv.qualificationtest.model.Crit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CritDao extends JpaRepository<Crit, Long> {
}
