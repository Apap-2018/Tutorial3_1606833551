package com.apap.tutorial3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.apap.tutorial3.model.PilotModel;

@Service
public class PilotInMemoryService implements PilotService {
	private List<PilotModel> archievePilot;

	public PilotInMemoryService() {
		archievePilot = new ArrayList<>();
	}
	@Override
	public void addPilot(PilotModel pilot) {
		archievePilot.add(pilot);
		
	}
	@Override
	public List<PilotModel> getPilotList() {
		// TODO Auto-generated method stub
		return archievePilot;
	}

	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		// TODO Auto-generated method stub
		PilotModel pilot = null;
		for (PilotModel pil: archievePilot) {
			if(pil.getLicenseNumber().equalsIgnoreCase(licenseNumber)) {
				pilot = pil;
			}	
		}
		return pilot;
	}
	@Override
	public void deletePilot(PilotModel pilot) {
		// TODO Auto-generated method stub
		archievePilot.remove(pilot);
		
	}
	
	
	

}
