package com.onedatapoint.repo;
import com.onedatapoint.model.Response;

public interface ResponseRepository {
	public Iterable<Response> getResponses();
	
	public void save(Response response);
}
