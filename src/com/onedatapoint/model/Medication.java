package com.onedatapoint.model;

import com.onedatapoint.config.Config;

public class Medication {

	private String id;
	private String label;
	private Integer hours = 12;
	private Integer minutes = 0;
	private Integer dose;
	private String units = "mg";

	public Medication(String id, String label, Integer hours, Integer minutes, Integer dose, String units) {
		this.id = id;
		this.label = label;
		this.hours = hours;
		this.minutes = minutes;
		this.dose = dose;
		this.units = units;
	}
	
	public static Iterable<Medication> getAll() {
		return Config.getInstance().getMedicationRepository().getMedications();
	}
	
	public void save() {
		Config.getInstance().getMedicationRepository().save(this);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	public Integer getDose() {
		return dose;
	}

	public void setDose(Integer dose) {
		this.dose = dose;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

}
