package com.onedatapoint.config;

import com.onedatapoint.repo.*;

public class Config {

	private static Config instance = null;
	private QuestionRepository questionRepository;
	private MedicationRepository medicationRepository;
	private ResponseRepository responseRepository;
	

	/**
	 * Use Config.getInstance()
	 */
	private Config() {
		questionRepository = new QuestionRepositoryImpl();
	} 
	
	public static synchronized Config getInstance() {
		if (Config.instance == null) {
			Config.instance = new Config();
		}
		return Config.instance;
	}
	
	public QuestionRepository getQuestionRepository() {
		return questionRepository;
	}
	
	public ResponseRepository getResponseRepository() {
		return responseRepository;
	}
	
	public MedicationRepository getMedicationRepository() {
		return medicationRepository;
	}
	
}
