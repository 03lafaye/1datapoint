package com.onedatapoint.repo;

import java.util.ArrayList;

import com.onedatapoint.model.Question;
import com.onedatapoint.model.XYQuestion;


public class QuestionRepositoryImpl implements QuestionRepository {

	ArrayList<Question> questions = new ArrayList<Question>();
	
	public QuestionRepositoryImpl() {
		questions.add(new XYQuestion("efa15dc2afaa418c9e0d7303fc2bed3b",
				"Rate your levels of:",
				"Anxiety", "Irritablity"));

		questions.add(new XYQuestion("cbdd0425e24c4353a3cc9d31d3bf0b06",
				"Rate your levels of:",
				"Concentration", "Decision Making Ability"));

		questions.add(new XYQuestion("e6dca97d9a024eb68464f10200c546f1",
				"Rate your levels of:",
				"Anxiety", "Concentration"));

		questions.add(new XYQuestion("0a8ec300078e435db81ebc4e3d6433e1",
				"Rate your levels of:",
				"Euphoria", "Concentration"));
		
		questions.add(new XYQuestion("f738ff793c004bf3ac0ef3c96177ac91",
				"Rate your levels of:",
				"Depression", "Decision Making Ability"));
	}
	
	public Iterable<Question> getQuestions() {
		return questions;
	}

	public void save(Question question) {
		throw new UnsupportedOperationException("Someone should implement this");
	}
	
}
