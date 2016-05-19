package com.solveast.muleteer.controller.debughelpers;

import com.google.gson.GsonBuilder;
import com.solveast.muleteer.repositories.MuleTrackRepository;
import com.solveast.muleteer.service.cache.MuleTrackCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by TEST on 17.05.2016.
 */
@Controller
public class AdminPanelHelperController {
    @Autowired
    MuleTrackCacheService muleTrackCacheService;

    @Autowired
    MuleTrackRepository muleTrackRepository;

    @RequestMapping(value = "/admin/mule/del-mem", produces = "application/json")
    @ResponseBody
    public String delMem() {
        return new GsonBuilder()
                .disableHtmlEscaping()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .serializeNulls()
                .create()
                .toJson(muleTrackCacheService.getMulesDeleted());
    }

    @RequestMapping(value = "/tracking/mule/del-db", produces = "application/json")
    @ResponseBody public String delBd() {
        return new GsonBuilder()
                .disableHtmlEscaping()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .serializeNulls()
                .create()
                .toJson(muleTrackRepository.findAll());
    }
}
