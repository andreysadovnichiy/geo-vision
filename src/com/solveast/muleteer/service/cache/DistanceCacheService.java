package com.solveast.muleteer.service.cache;

import com.solveast.muleteer.model.Distance;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TEST on 16.05.2016.
 */
@Service
public class DistanceCacheService {
    private List<Distance> distances = new ArrayList<>();

    public List<Distance> getDistances() {
        return distances;
    }

    public List<Distance> getDistancesAndClearCache() {
        List<Distance> tmpDist = new ArrayList<>(distances);
        distances.clear();
        return tmpDist;
    }

    public void add(final Distance distance) {
        distances.add(distance);
    }
}
