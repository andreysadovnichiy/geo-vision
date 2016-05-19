package com.solveast.muleteer.service.jpa;

import com.solveast.muleteer.model.TrackingMode;
import com.solveast.muleteer.repositories.TrackingModeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by TEST on 16.05.2016.
 */
@Service
@Transactional
public class TrackingModeService {
    @Autowired TrackingModeRepository trackingModeRepository;

    public TrackingMode save(final TrackingMode toSave) {
        return trackingModeRepository.save(toSave);
    }
}
