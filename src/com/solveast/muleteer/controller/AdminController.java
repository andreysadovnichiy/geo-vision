package com.solveast.muleteer.controller;

import com.google.gson.GsonBuilder;
import com.solveast.muleteer.model.Muletrack;
import com.solveast.muleteer.repositories.MuleTrackRepository;
import com.solveast.muleteer.service.cache.MuleTrackCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {

    @Autowired
    MuleTrackCacheService muleTrackCacheService;

    @Autowired
    MuleTrackRepository muleTrackRepository;

    //@Autowired private LogService logService;

    @RequestMapping("/admin")
    public String admin(final Model model) {
        model.addAttribute("mules", muleTrackCacheService.findAllAdminView());
        return "admin";
    }

    @RequestMapping(value = "/admin/mule-{id}", method = RequestMethod.POST)
    @ResponseBody
    public Muletrack adminUpdateNameAndActive(final String name, final boolean status, @PathVariable final int id) {
        Muletrack mule = muleTrackCacheService.updateNameActiveStatus(id, name, status);
        if (mule != null) {
//            logService.add(mule.getId() + " " + mule.getName() + " " + mule.isActive());
            return mule;
        }
        return null;
    }

    @RequestMapping(value = "/admin/del/mule-{id}", method = RequestMethod.POST)
    @ResponseBody public boolean deleteById(@PathVariable final int id) {
        boolean isDeleted = muleTrackCacheService.deleteById(id);
        return isDeleted;
    }

    @RequestMapping(value = "/admin/get-new-mule")
    @ResponseBody public String getNewMule() {
        return new GsonBuilder()
                .disableHtmlEscaping()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .serializeNulls()
                .create()
                .toJson(muleTrackCacheService.getNewMule());
    }
}
