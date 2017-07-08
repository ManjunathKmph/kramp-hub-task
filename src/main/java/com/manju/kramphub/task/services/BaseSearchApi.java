package com.manju.kramphub.task.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manju.kramphub.task.exceptions.KrampHubTaskException;

/**
 * Class uses url connection to fetch the response from the search api's.
 * 
 * @author manju
 * @version 1.0
 *
 */
public abstract class BaseSearchApi {
	
	private static final Logger LOG = LoggerFactory.getLogger(BaseSearchApi.class);
	
	protected String apiUrl;
	
	public BaseSearchApi() {
	}

	/**
	 * Connects  to the search url to fetch response.
	 * 
	 * @param searchValue -- Value to search in the search api
	 * @param limit -- Limit the response from the search api
	 * @return -- Returns the response in the format of json data.
	 */
	public String searchApi(String searchValue, int limit) {
		URL url = null;
		BufferedReader reader = null;
		String result = null;
		try {
			url = constructSearchUrl(searchValue, limit);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			result = readDataFromResponseStream(reader);
		} catch (MalformedURLException e) {
			LOG.error("Search url is not proper", e);
			throw new KrampHubTaskException("Search url is not proper,", e);
		} catch (IOException e) {
			LOG.error("Error in getting the response from search url", e);
			throw new KrampHubTaskException("Error in getting the response from search url.", e);
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
		}
		return result;
	}
	
	/**
	 * Read the characters from the url stream reader.
	 * 
	 * @param responseReader -- Reader which holds the stream to the url.
	 * @return -- Return the read characters from the stream reader.
	 * @throws IOException -- Throws exception in case of any error while reading the character.
	 */
	private String readDataFromResponseStream(BufferedReader responseReader) throws IOException {
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = responseReader.readLine()) != null) {
            builder.append(line);
        }
        return builder.toString();
    }
	
	/**
	 * Encodes the search parameter according the search api.
	 * 
	 * @param searchValue -- Value to encode
	 * @return -- Returns the encoded string of value
	 * @throws UnsupportedEncodingException -- Throws exception in case of encoding format not supported.
	 */
	protected abstract String encodeSearchParams(String searchValue) throws UnsupportedEncodingException;
	
	/**
	 * Construct the url according each search api.
	 * 
	 * @param searchValue -- Value to search in the search api
	 * @param limit -- Limit the response
	 * @return -- Returns the constructed url object
	 * @throws MalformedURLException -- Throws exception in case of malformed url
	 * @throws UnsupportedEncodingException -- Throws exception in case of Unsupported encoding format.
	 */
	protected abstract URL constructSearchUrl(String searchValue, int limit) throws MalformedURLException, UnsupportedEncodingException;
}
