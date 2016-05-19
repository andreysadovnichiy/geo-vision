package com.solveast.muleteer.service.cron;

import com.solveast.muleteer.service.cache.DistanceCacheService;
import com.solveast.muleteer.service.jpa.DistanceService;
import com.solveast.muleteer.service.jpa.MuleTrackService;
import com.solveast.muleteer.service.cache.MuleTrackCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by TEST on 06.05.2016.
 */
@Service
public class SaveSqlDbCronService {
    @Autowired
    MuleTrackCacheService muleTrackCacheService;

    @Autowired
    DistanceCacheService distanceCacheService;

    @Autowired
    MuleTrackService muleTrackDbService;

    @Autowired
    DistanceService distanceDbService;

    // every odd day    0 0 0 1-31/2 * *
    // midnight         0 0 0 * * *
    // every hour       0 0 * * * *
    // every minute     0 * * * * *
    // every 5 min      0 0/5 * * * ?
    @Scheduled(cron = "0 0/5 * * * ?")
    @Transactional
    public void saveAllCache() {
        muleTrackDbService.processDelete(muleTrackCacheService.getMulesDeleted());
        muleTrackDbService.saveAll(muleTrackCacheService.findAll());
    }

    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void processDistance() {
        distanceDbService.save(distanceCacheService.getDistancesAndClearCache());
    }

    @Scheduled(cron = "0 0 0 1-31/2 * * ")
    public void clearDistanceDb() {
        distanceDbService.deleteAll();
    }
}
