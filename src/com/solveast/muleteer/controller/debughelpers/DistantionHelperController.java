package com.solveast.muleteer.controller.debughelpers;

import com.google.gson.GsonBuilder;
import com.solveast.muleteer.model.Muletrack;
import com.solveast.muleteer.service.jpa.DistanceService;
import com.solveast.muleteer.service.cache.MuleTrackCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by TEST on 16.05.2016.
 */
@RestController
public class DistantionHelperController {
    @Autowired
    DistanceService distanceService;

    @Autowired
    MuleTrackCacheService cacheService;

    @RequestMapping(value = "/get-dist", produces = "application/json")
    public String getDistance() {
        return new GsonBuilder()
                .disableHtmlEscaping()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .serializeNulls()
                .create()
                .toJson(distanceService.findAll());
    }

    @RequestMapping(value = "/get-dist-{id}", produces = "application/json")
    public String getDistanceById(@PathVariable final int id) {
        Muletrack mule = cacheService.getById(id);
        return new GsonBuilder()
                .disableHtmlEscaping()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .serializeNulls()
                .create()
                .toJson(distanceService.findByMule(mule));
    }
}
