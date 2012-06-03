package com.onedatapoint.repo;

import java.util.HashMap;

import com.onedatapoint.model.Medication;

public class MedicationRepositoryImpl implements MedicationRepository {

	private HashMap<String, Medication> medications;
	
	public MedicationRepositoryImpl() {
		this.medications = new HashMap<String, Medication>();
	}
	
	public Iterable<Medication> getMedications() {
		return medications.values();
	}

	public void save(Medication medication) {
		medications.put(medication.getId(), medication);
	}

}
