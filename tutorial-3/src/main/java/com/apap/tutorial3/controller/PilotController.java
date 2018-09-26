package com.apap.tutorial3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial3.model.PilotModel;
import com.apap.tutorial3.service.PilotService;

@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;

	@RequestMapping("/pilot/add")
	public String add(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "licenseNumber", required = true) String licenseNumber,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "flyHour", required = true) int flyHour) {
		PilotModel pilot = new PilotModel(id, licenseNumber, name, flyHour);
		pilotService.addPilot(pilot);
		return "add";
	}

	@RequestMapping("/pilot/view")
	public String view(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		model.addAttribute("pilot", archive);
		return "view-pilot";

	}

	@RequestMapping("/pilot/viewall")
	public String viewall(Model model) {
		List<PilotModel> archive = pilotService.getPilotList();
		model.addAttribute("listPilot", archive);
		return "viewall-pilot";
	}

	@RequestMapping("/pilot/view/license-number/{licenseNumber}")
	public String viewByLicense(@PathVariable String licenseNumber, Model model) {
		PilotModel found = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		if (found == null) {
			return "error";
		} else {
			model.addAttribute("pilot", found);
		}
		return "view-pilot";
	}

	@RequestMapping("/pilot/update/license-number/{licenseNumber}/fly-hour/{flyHour}")
	public String editFlyHours(@PathVariable String licenseNumber, @PathVariable Integer flyHour, Model model) {
		PilotModel found = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		if (found == null) {
			return "error";
		} else {
			found.setFlyHour(flyHour);
			model.addAttribute("pilot", found);
		}
		return "updateFH";

	}
	@RequestMapping("/pilot/delete/id/{id}") 
	public String deleteById(@PathVariable String id, Model model) {
		List<PilotModel> archive = pilotService.getPilotList();
		for (int i=0; i<archive.size(); i++) {
			PilotModel pilot = archive.get(i);
			
			if(pilot.getId().equals(id)) {
				pilotService.deletePilot(pilot);
				model.addAttribute("pilot", pilot);
				return "deleteSukses";
			}
		}
		return "error";
	}
	

}
