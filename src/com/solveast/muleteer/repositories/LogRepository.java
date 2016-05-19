package com.solveast.muleteer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solveast.muleteer.model.Log;

public interface LogRepository extends JpaRepository<Log, Integer> {

}
