package tourguide.trip;

import java.util.List;

import tourguide.model.AttractionNearby;
import tourguide.model.ProviderData;
import tourguide.model.User;

public interface TripService {

	final static String TRIP_PRICER_KEY = "test-server-api-key";

	List<ProviderData> calculateProposals(User user, List<AttractionNearby> attractions, int cumulativeRewardPoints);

}