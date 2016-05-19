package com.solveast.muleteer.controller;

import com.google.gson.GsonBuilder;
import com.solveast.muleteer.model.TrackingMode;
import com.solveast.muleteer.service.jpa.TrackingModeService;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by TEST on 16.05.2016.
 */
@Controller
public class TrackingModeController {
    @Autowired
    TrackingModeService trackingModeService;

    @RequestMapping(value = "/admin/period/mule-{id}", method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    @ResponseBody
    public String setPeriod(@RequestBody final String json, @PathVariable final int id) {
//      jsonToSend: {"state":"ON","id":1,"switchTime":1463152826442}
        TrackingMode trackingMode = new TrackingMode();
        final ObjectMapper mapper = new ObjectMapper();

        try {
            trackingMode = mapper.readValue(json, TrackingMode.class);
        } catch (final JsonParseException e) {
            e.printStackTrace();
        } catch (final JsonMappingException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        trackingMode.setId(id);
        trackingModeService.save(trackingMode);

        return new GsonBuilder()
                .disableHtmlEscaping()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .serializeNulls()
                .create()
                .toJson(trackingMode);
    }
}
