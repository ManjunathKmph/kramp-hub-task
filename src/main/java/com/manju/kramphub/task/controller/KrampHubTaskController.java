package com.manju.kramphub.task.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manju.kramphub.task.model.ApiHealthStatus;
import com.manju.kramphub.task.model.KrampHubTaskResponse;
import com.manju.kramphub.task.model.Metrics;
import com.manju.kramphub.task.model.TaskResponse;
import com.manju.kramphub.task.services.KrampHubTaskService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller class delegates the search request to service class to process the request.
 * 
 * @author manju
 * @version 1.0
 *
 */
@RestController
@Api(value = "/task", description = "Task context Path")
public class KrampHubTaskController {
	
	private static final Logger LOG = LoggerFactory.getLogger(KrampHubTaskController.class);
	
	@Autowired
	private KrampHubTaskService krampHubTaskService;
	
	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value = "List of Kramp Hub Task response", notes = "List of Kramp Hub Task response for seach value", response = KrampHubTaskResponse.class, responseContainer = "List")
	@ApiResponses({
		@ApiResponse(code = 404, message = "Query term cannot be empty."),
		@ApiResponse(code = 405, message = "Request Method not supported"),
		@ApiResponse(code = 500, message = "Internal Server while processing the request"),
		@ApiResponse(code = 200, message = "Successfully processed the request", reference="{\"count\":2,\"taskResponses\":[{\"title\":\"Mr. Misunderstood\",\"personName\":\"Eric Church\",\"type\":\"ALBUM\"},{\"title\":\"Thesaurus of ERIC Descriptors\",\"personName\":\"[\\\"Educational Resources Information Center (U.S.)\\\",\\\"James E. Houston\\\"]\",\"type\":\"BOOK\"}],\"apiMetrics\":{\"apiStartTime\":\"2017-07-07T21:55:37+0530\",\"apiEndTime\":\"2017-07-07T21:55:38+0530\",\"apiCompleteTime\":\"1.247 Seconds\"}}")
	})
	public ResponseEntity<?> fetchResults(@RequestParam("q") String searchValue, @RequestParam(name="limit", defaultValue="5") Integer limit) {
		if(searchValue == "" || searchValue == null || searchValue.trim().length() == 0 || !searchValue.matches("[a-zA-Z0-9 -_\\.!]+$")) {
			throw new IllegalArgumentException("Invalid query value.");
		}
		long startTime = System.currentTimeMillis();
		LOG.debug("Start of search service. Argument values -> searchValue:" + searchValue + " limit:" + limit);
		KrampHubTaskResponse krampHubTaskResponse = new KrampHubTaskResponse();
		Metrics metrics = new Metrics();
		ApiHealthStatus apiHealthStatus = new ApiHealthStatus();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		metrics.setApiStartTime(dateFormat.format(new Date(startTime))); //Setting the api start time with formatted date.
		List<TaskResponse> resultList = krampHubTaskService.search(searchValue, limit, apiHealthStatus);
		krampHubTaskResponse.setCount(resultList.size());
		krampHubTaskResponse.setTaskResponses(resultList);
		long endTime = System.currentTimeMillis();
		krampHubTaskResponse.setApiMetrics(metrics);
		krampHubTaskResponse.setApiHealthStatus(apiHealthStatus);
		metrics.setApiEndTime(dateFormat.format(new Date(endTime))); //Setting the api end time with formatted date.
		metrics.setApiCompleteTime((endTime - startTime) / 1000.0 + " Seconds"); //Setting the api total time to process the request.
		LOG.debug("End of search service.");
		return ResponseEntity.ok(krampHubTaskResponse);
	}

}
