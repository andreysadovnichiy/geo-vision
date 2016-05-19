package com.solveast.muleteer.controller;

import com.google.gson.GsonBuilder;
import com.solveast.muleteer.model.Muletrack;
import com.solveast.muleteer.service.cache.MuleTrackCacheService;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class MuleController {
    @Autowired
    MuleTrackCacheService muleTrackCacheService;

//    @Autowired LogService logService;

    private static final Logger logger = LoggerFactory.getLogger(MuleController.class);

    @RequestMapping(value = "/tracking/mule-{id}",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody public String consumeLatLngDistanceDateFromDevice(@RequestBody final String json, @PathVariable final int id) {

        Muletrack muletrack = new Muletrack();
        final ObjectMapper mapper = new ObjectMapper();
//        logService.add(id+": "+json);

        try {
            muletrack = mapper.readValue(json, Muletrack.class);
            muletrack.setId(id);
        } catch (final JsonParseException e) {
            e.printStackTrace();
//            logService.add("JsonParseException when proccessing coordinates");
        } catch (final JsonMappingException e) {
            e.printStackTrace();
//            logService.add("JsonMappingException when proccessing coordinates");
        } catch (final IOException e) {
            e.printStackTrace();
//            logService.add("IOException when proccessing coordinates");
        }

        return new GsonBuilder()
                .disableHtmlEscaping()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .serializeNulls()
                .create()
                .toJson(muleTrackCacheService.updateLatLngDistanceDate(muletrack));
    }
}
