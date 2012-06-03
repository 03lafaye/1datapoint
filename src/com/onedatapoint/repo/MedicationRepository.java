package com.onedatapoint.repo;

import com.onedatapoint.model.Medication;

public interface MedicationRepository {

	Iterable<Medication> getMedications();
	void save(Medication medication);
	
}
