package com.solveast.muleteer.controller.debughelpers;

import com.google.gson.GsonBuilder;
import com.solveast.muleteer.repositories.MuleTrackRepository;
import com.solveast.muleteer.service.cache.MuleTrackCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by TEST on 11.05.2016.
 */
@Controller
public class MarkersHelperController {
    @Autowired
    MuleTrackCacheService muleTrackCacheService;

    @Autowired
    MuleTrackRepository muleTrackRepository;

//    private static final Logger logger = LoggerFactory.getLogger(MarkersHelperController.class);

    @RequestMapping(value = "/get-markers", produces = "application/json")
    @ResponseBody
    public String getMarkers() {
        return new GsonBuilder()
                .disableHtmlEscaping()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .serializeNulls()
                .create()
                .toJson(muleTrackCacheService.getMulesByActiveTrue());
    }

    @RequestMapping(value = "/get-markers-db", produces = "application/json")
    @ResponseBody
    public String getMarkersDb() {
        return new GsonBuilder()
                .disableHtmlEscaping()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .serializeNulls()
                .create()
                .toJson(muleTrackRepository.findAll());
    }

    @RequestMapping(value = "/get-markers-map", produces = "application/json")
    @ResponseBody
    public String getMarkersMap() {
        return new GsonBuilder()
                .disableHtmlEscaping()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .serializeNulls()
                .create()
                .toJson(muleTrackCacheService.findAllAdminView());
    }

    @RequestMapping(value = "/get-markers-mem", produces = "application/json")
    @ResponseBody
    public String getMarkersMem() {
        return new GsonBuilder()
                .disableHtmlEscaping()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .serializeNulls()
                .create()
                .toJson(muleTrackCacheService.findAll());
    }

    @RequestMapping(value = "/get-markers-del", produces = "application/json")
    @ResponseBody
    public String getMarkersDel() {
        return new GsonBuilder()
                .disableHtmlEscaping()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .serializeNulls()
                .create()
                .toJson(muleTrackCacheService.getMulesDeleted());
    }
}
