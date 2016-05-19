package com.solveast.muleteer.repositories;

import com.solveast.muleteer.model.Distance;
import com.solveast.muleteer.model.Muletrack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by TEST on 12.05.2016.
 */
public interface DistanceRepository extends JpaRepository<Distance, Integer> {
    public List<Distance> findById(int id);

    public List<Distance> findByMule(Muletrack mule);
}
