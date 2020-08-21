package tourguide.api;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tourguide.model.AttractionNearby;
import tourguide.model.ProposalForm;
import tourguide.model.ProviderData;
import tourguide.model.User;

@Service
public class TripRequest {

	private Logger logger = LoggerFactory.getLogger(TripRequest.class);
	private final TripClient tripClient;
	@Autowired private ObjectMapper objectMapper;

	public TripRequest(TripClient tripClient) {
		this.tripClient = tripClient;
	}
	
	public List<ProviderData> calculateProposals(User user, List<AttractionNearby> attractions,	int cumulativeRewardPoints) {
		logListContent("calculateProposals before external call", Collections.singletonList(user));
		logListContent("calculateProposals before external call", attractions);
		logListContent("calculateProposals before external call", Collections.singletonList(cumulativeRewardPoints));
		ProposalForm proposalForm = new ProposalForm(user, attractions, cumulativeRewardPoints);
		List<ProviderData> proposals = tripClient.calculateProposals(proposalForm);
		logListContent("calculateProposals after external call", proposals);
		return proposals;
	}
	
	private void logListContent(String methodName, List<?> list) {
		logger.debug(methodName + " number of elements " + list.size() + " : " + list.toString());
		try {
			logger.debug(methodName + " content details : " + objectMapper.writeValueAsString(list));
		} catch (JsonProcessingException e) {
			throw new RuntimeException("logListContent catched a JsonProcessingException");
		}
	}
}
