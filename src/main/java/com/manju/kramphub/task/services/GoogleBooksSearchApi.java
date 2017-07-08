package com.manju.kramphub.task.services;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.manju.kramphub.task.common.KrampHubTaskConstants;

/**
 * Implementation for google books search api.
 * 
 * @author manju
 * @version 1.0
 * @see com.manju.kramphub.task.services.BaseSearchApi
 *
 */
@Component("googleBookSearchApi")
public class GoogleBooksSearchApi extends BaseSearchApi {
	
	@Value("${google.books.search.url}")
	protected String apiUrl;
	
	private final static String predicate = "q=";
	
	private final static String LIMIT = "maxResults=";
	
	/* (non-Javadoc)
	 * @see com.manju.kramphub.task.services.BaseSearchApi#encodeSearchParams(java.lang.String)
	 */
	@Override
	protected String encodeSearchParams(String searchValue) throws UnsupportedEncodingException {
		String encodedQuery = URLEncoder.encode(searchValue, "UTF-8");
		return KrampHubTaskConstants.QUESTION_MARK + predicate + encodedQuery;
	}
	
	/* (non-Javadoc)
	 * @see com.manju.kramphub.task.services.BaseSearchApi#constructSearchUrl(java.lang.String, int)
	 */
	@Override
	protected URL constructSearchUrl(String searchValue, int limit) throws MalformedURLException, UnsupportedEncodingException {
		return new URL(apiUrl + encodeSearchParams(searchValue) +  KrampHubTaskConstants.AMPERSAND + LIMIT + limit);
	}

}
