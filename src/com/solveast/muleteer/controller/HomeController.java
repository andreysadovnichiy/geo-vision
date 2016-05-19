package com.solveast.muleteer.controller;

import com.solveast.muleteer.service.cache.MuleTrackCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by TEST on 11.05.2016.
 */
@Controller
public class HomeController {
    @Autowired
    MuleTrackCacheService muleTrackCacheService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/")
    public String goHome(final Model model) {
        model.addAttribute("mules", muleTrackCacheService.getMulesByActiveTrue());
        return "index";
    }
}
