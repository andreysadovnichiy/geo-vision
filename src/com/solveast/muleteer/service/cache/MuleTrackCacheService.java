package com.solveast.muleteer.service.cache;

import com.solveast.muleteer.model.Distance;
import com.solveast.muleteer.model.Muletrack;
import com.solveast.muleteer.model.TrackingMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by TEST on 29.04.2016.
 */
@Service
public class MuleTrackCacheService {

    private static final Logger logger = LoggerFactory.getLogger(MuleTrackCacheService.class);

    @Autowired
    DistanceCacheService distanceCacheService;

//    @Autowired private LogService logService;

    private List<Muletrack> mules = new ArrayList<>();

    private Set<Muletrack> mulesDeleted = new LinkedHashSet<>();

    public Set<Muletrack> getMulesDeleted() {
        return mulesDeleted;
    }

    public List<Muletrack> getMulesByActiveTrue() {
        return mules.stream()
                .filter(Muletrack::isActive)
                .collect(Collectors.toList());
    }

    public void setMules(final List<Muletrack> sourceMules) {
        mules.clear();
        mules.addAll(sourceMules);
    }

    public Muletrack updateLatLngDistanceDate(final Muletrack muletrack) {
        if (muletrack.isNotNull()) {
            mules.stream()
                    .filter(mule -> mule.getId() == muletrack.getId())
                    .forEach(mule -> {
/*
                    Добавить:
                     1. Сохранение пройденного расстояния за день/интервал в новую таблицу
*/
                        if (mule.isTrackingActiveNow()) {
                            mule.setLat(muletrack.getLat());
                            mule.setLng(muletrack.getLng());
                            mule.setDate(muletrack.getDate());
                            mule.setDistance(muletrack.getDistance());
                        }

                        final Distance distance = new Distance(muletrack);
                        distanceCacheService.add(distance);
                    });
        }
        return getById(muletrack.getId());
    }

    public Muletrack getById(final int id) {
        return mules.stream()
                .filter(mule -> mule.getId() == id)
                .findFirst()
                .get();
    }

    public List<Muletrack> findAll() {
        return mules.stream()
                .sorted((a1, a2) -> a1.getId() - a2.getId())
                .collect(Collectors.toList());
    }

    public Map<Integer, Muletrack> findAllAdminView() {
        return mules.stream()
                .collect(Collectors.toMap(Muletrack::getId, Function.identity()));
    }

    public Muletrack updateNameActiveStatus(final int id, final String name, final boolean status) {
        mules.stream()
                .filter(mule -> mule.getId() == id)
                .forEach(mule -> {
                    mule.setActive(status);
                    mule.setName(name);
                });
        return getById(id);
    }

    public boolean deleteById(final int id) {

        try {
            Muletrack mule = getById(id);

            if (mule != null) {
                mulesDeleted.add(mule);
                mules.remove(mules.indexOf(mule));
                return true;
            } else if (mule == null) {
                System.out.println("Mule null!!");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("deleteById IndexOutOfBoundsException");
        }

        return false;
    }

    public Muletrack getNewMule() {
        int id = 1;

        if (!mules.isEmpty()) {
            id = (mules.stream()
                    .map(mule -> mule.getId())
                    .max(Comparator.naturalOrder())
                    .get()) + 1;
        }

        Muletrack mule = new Muletrack(id, "name" + id, false);
        mule.setCurrentTrackingMode(TrackingMode.setDefaultMode(mule));
        mules.add(mule);
        return mule;
    }
}
