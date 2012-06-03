package com.onedatapoint.repo;
import com.onedatapoint.model.Question;

public interface QuestionRepository {
	public Iterable<Question> getQuestions();
	
	public void save(Question question);
}
