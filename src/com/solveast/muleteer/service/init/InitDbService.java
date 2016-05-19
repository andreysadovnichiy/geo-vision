package com.solveast.muleteer.service.init;

import com.solveast.muleteer.model.Distance;
import com.solveast.muleteer.model.Muletrack;
import com.solveast.muleteer.model.TrackingMode;
import com.solveast.muleteer.repositories.DistanceRepository;
import com.solveast.muleteer.repositories.MuleTrackRepository;
import com.solveast.muleteer.repositories.TrackingModeRepository;
import com.solveast.muleteer.service.cache.MuleTrackCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Transactional
@Service
public class InitDbService {

    private static final Logger logger = LoggerFactory.getLogger(InitDbService.class);

    @Autowired DistanceRepository distanceRepository;

    @Autowired Environment env;

    @Autowired private MuleTrackRepository muleTrackRepository;

    @Autowired private TrackingModeRepository trackingModeRepository;

    @Autowired private MuleTrackCacheService cacheService;

    private boolean isTestEnvOnProd = false;

    //@PostConstruct
    public void init() {
        // create time zone object
        TimeZone serverTimeZone = TimeZone.getTimeZone("Europe/Kiev");
        // set time zone to default
        serverTimeZone.setDefault(serverTimeZone);


        List<String> activeProfiles = Arrays.asList(env.getDefaultProfiles());

        if (activeProfiles.contains("dev")) {

            List<Muletrack> muletracks = Arrays.asList(new Muletrack(1, "Truck I", true),
                    new Muletrack(2, "Truck II", true),
                    new Muletrack(3, "Truck III", true),
                    new Muletrack(4, "Truck IV", true));

            muleTrackRepository.save(muletracks);

            List<TrackingMode> trackingModes = Arrays.asList(TrackingMode.setDefaultMode(muletracks.get(0)),
                    TrackingMode.setDefaultMode(muletracks.get(1)),
                    TrackingMode.setDefaultMode(muletracks.get(2)),
                    TrackingMode.setDefaultMode(muletracks.get(3))
            );

            muletracks.get(0).setCurrentTrackingMode(trackingModes.get(0));
            muletracks.get(1).setCurrentTrackingMode(trackingModes.get(1));
            muletracks.get(2).setCurrentTrackingMode(trackingModes.get(2));
            muletracks.get(3).setCurrentTrackingMode(trackingModes.get(3));

            trackingModeRepository.save(trackingModes);
            cacheService.setMules(muletracks);
        } else if (activeProfiles.contains("prod") && isTestEnvOnProd) {
            muleTrackRepository.deleteAll();
            trackingModeRepository.deleteAll();
            distanceRepository.deleteAll();

            List<Muletrack> muletracks = Arrays.asList(new Muletrack(1, "Truck I", true),
                    new Muletrack(2, "Truck II", true));

            muleTrackRepository.save(muletracks);

            List<TrackingMode> trackingModes = Arrays.asList(TrackingMode.setDefaultMode(muletracks.get(0)),
                    TrackingMode.setDefaultMode(muletracks.get(1))
            );

            muletracks.get(0).setCurrentTrackingMode(trackingModes.get(0));
            muletracks.get(1).setCurrentTrackingMode(trackingModes.get(1));

            trackingModeRepository.save(trackingModes);

            cacheService.setMules(muletracks);
        } else if (activeProfiles.contains("prod") && !isTestEnvOnProd) {
            cacheService.setMules(muleTrackRepository.findAll());
        }

//        append4Testing();
    }

    @PostConstruct
    public void initSharedKet() {
        // create time zone object
        TimeZone serverTimeZone = TimeZone.getTimeZone("Europe/Kiev");
        // set time zone to default
        serverTimeZone.setDefault(serverTimeZone);


        List<String> activeProfiles = Arrays.asList(env.getDefaultProfiles());

        if (activeProfiles.contains("dev")) {

            List<Muletrack> muletracks = Arrays.asList(new Muletrack(1, "Truck I", true),
                    new Muletrack(2, "Truck II", true),
                    new Muletrack(3, "Truck III", true),
                    new Muletrack(4, "Truck IV", true));

            List<TrackingMode> trackingModes = Arrays.asList(TrackingMode.setDefaultMode(muletracks.get(0)),
                    TrackingMode.setDefaultMode(muletracks.get(1)),
                    TrackingMode.setDefaultMode(muletracks.get(2)),
                    TrackingMode.setDefaultMode(muletracks.get(3))
            );

            muletracks.get(0).setCurrentTrackingMode(trackingModes.get(0));
            muletracks.get(1).setCurrentTrackingMode(trackingModes.get(1));
            muletracks.get(2).setCurrentTrackingMode(trackingModes.get(2));
            muletracks.get(3).setCurrentTrackingMode(trackingModes.get(3));

            trackingModes.get(0).setMule(muletracks.get(0));
            trackingModes.get(1).setMule(muletracks.get(1));
            trackingModes.get(2).setMule(muletracks.get(2));
            trackingModes.get(3).setMule(muletracks.get(3));

            //muleTrackRepository.save(muletracks);

            Muletrack mule = new Muletrack(1, "Truck I", true);
            TrackingMode mode = TrackingMode.setDefaultMode();
            mule.setCurrentTrackingMode(mode);
            mode.setMule(mule);

            muleTrackRepository.save(mule);

            cacheService.setMules(muletracks);
        } else if (activeProfiles.contains("prod") && isTestEnvOnProd) {
            muleTrackRepository.deleteAll();
            trackingModeRepository.deleteAll();
            distanceRepository.deleteAll();

            List<Muletrack> muletracks = Arrays.asList(new Muletrack(1, "Truck I", true),
                    new Muletrack(2, "Truck II", true));



            List<TrackingMode> trackingModes = Arrays.asList(TrackingMode.setDefaultMode(muletracks.get(0)),
                    TrackingMode.setDefaultMode(muletracks.get(1))
            );

            muletracks.get(0).setCurrentTrackingMode(trackingModes.get(0));
            muletracks.get(1).setCurrentTrackingMode(trackingModes.get(1));

            muleTrackRepository.save(muletracks);
            cacheService.setMules(muletracks);
        } else if (activeProfiles.contains("prod") && !isTestEnvOnProd) {
            cacheService.setMules(muleTrackRepository.findAll());
        }

//        append4Testing();
    }

    private void append4Testing() {
        List<Distance> distances = Arrays.asList(new Distance(2, new Date(), cacheService.getById(1)),
                new Distance(3, new Date(System.currentTimeMillis() + 10000), cacheService.getById(1)),
                new Distance(1, new Date(System.currentTimeMillis() + 15000), cacheService.getById(1)),
                new Distance(1, new Date(System.currentTimeMillis() + 5000), cacheService.getById(2))
        );
        distanceRepository.save(distances);
    }
}
