package com.onedatapoint.repo;

import java.util.HashMap;

import com.onedatapoint.model.Response;

public class ResponseRepositoryImpl implements ResponseRepository {

	HashMap<String, Response> responses;
	
	public ResponseRepositoryImpl() {
		responses = new HashMap<String, Response>();
	}
	
	public Iterable<Response> getResponses() {
		return responses.values();
	}

	public void save(Response response) {
		responses.put(response.getId(), response);
	}

}
