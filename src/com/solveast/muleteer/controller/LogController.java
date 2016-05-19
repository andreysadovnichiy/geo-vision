package com.solveast.muleteer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.GsonBuilder;
import com.solveast.muleteer.service.log.LogService;

@Controller
public class LogController {

	@Autowired
	LogService logService;

	@RequestMapping(value = "/get-logs", produces = "application/json")
	@ResponseBody
	public String getLoggs() {
		return new GsonBuilder()
				.disableHtmlEscaping()
				.excludeFieldsWithoutExposeAnnotation()
				.setPrettyPrinting()
				.serializeNulls()
				.create()
				.toJson(logService.getAll());
	}

	@RequestMapping(value = "/log")
	public String getLoggPage(final Model model) {
		final List<String> logs = logService.getAll();

		model.addAttribute("logs", logs);
		model.addAttribute("total", logs.size());
		return "log";
	}
}
