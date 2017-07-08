package com.manju.kramphub.task.callback;

import java.util.List;
import java.util.concurrent.Callable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manju.kramphub.task.exceptions.KrampHubTaskException;
import com.manju.kramphub.task.model.TaskResponse;
import com.manju.kramphub.task.model.Type;
import com.manju.kramphub.task.services.BaseSearchApi;

/**
 * Class acts as a callback for google search api which makes the request to google books url to fetch the
 * books based on limit and search value. Class runs in it's separate stack trace since it extends the callable interface.
 * 
 * @author manju
 * @version 1.0
 * @see java.util.concurrent.Callable
 *
 */
public class GoogleSearchCallback implements Callable<Boolean> {
	
	private static final Logger LOG = LoggerFactory.getLogger(GoogleSearchCallback.class);
	
	private String searchValue;
	
	private int limit;
	
	private List<TaskResponse> resultList;
	
	private BaseSearchApi googleBookSearchApi;
	
	public GoogleSearchCallback(String searchValue, int limit, List<TaskResponse> resultList, BaseSearchApi googleBookSearchApi) {
		this.searchValue = searchValue;
		this.limit = limit;
		this.resultList = resultList;
		this.googleBookSearchApi = googleBookSearchApi;
	}

	/* (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public Boolean call() throws Exception {
		String responseJsonData = googleBookSearchApi.searchApi(searchValue, limit);
		convertBooksJsonToObject(responseJsonData, resultList);		
		return true;
	}
	
	/**
	 * Converts the response google json data into custom object.
	 * 
	 * @param jsonData -- Json Data
	 * @param resultList -- Result list holds the converted object from json.
	 */
	private void convertBooksJsonToObject(String jsonData, List<TaskResponse> resultList) {
		try {
			JSONObject object = new JSONObject(jsonData);
			JSONArray jsonArr = object.getJSONArray("items");
			TaskResponse responseObj = null;
			for(int i = 0; i < jsonArr.length(); i++) {
				responseObj = new TaskResponse();
				responseObj.setTitle(jsonArr.getJSONObject(i).getJSONObject("volumeInfo").getString("title"));
				responseObj.setPersonName(jsonArr.getJSONObject(i).getJSONObject("volumeInfo").getJSONArray("authors").toString());
				responseObj.setType(Type.BOOK);
				resultList.add(responseObj);
			}
		} catch (JSONException e) {
			LOG.error("Error while converting google response json to object.", e);
			throw new KrampHubTaskException("Error while converting google response json to object.", e);
		}
	}
	
}
