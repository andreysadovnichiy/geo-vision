package com.solveast.muleteer.repositories;

import com.solveast.muleteer.model.Muletrack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MuleTrackRepository extends JpaRepository<Muletrack, Integer> {

//	Muletrack findById(int id);

//	List<Muletrack> findByActiveTrue();
}
