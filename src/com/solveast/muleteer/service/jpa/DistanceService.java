package com.solveast.muleteer.service.jpa;

import com.solveast.muleteer.model.Distance;
import com.solveast.muleteer.model.Muletrack;
import com.solveast.muleteer.repositories.DistanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by TEST on 16.05.2016.
 */
@Service
@Transactional
public class DistanceService {
    @Autowired
    DistanceRepository distanceRepository;

    public Distance save(final Distance distance) {
        return distanceRepository.save(distance);
    }

    public List<Distance> save(final List<Distance> distances) {
        return distanceRepository.save(distances);
    }

    public List<Distance> findAll() {
        return distanceRepository.findAll();
    }

    public List<Distance> findByMule(final Muletrack mule) {
        return distanceRepository.findByMule(mule);
    }

    public void deleteAll() {
        distanceRepository.deleteAll();
    }
}
