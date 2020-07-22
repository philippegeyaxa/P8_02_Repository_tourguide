package tourguide.tripservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tourguide.model.AttractionNearby;
import tourguide.user.User;
import tripPricer.Provider;
import tripPricer.TripPricer;

@Service
public class TripService {
	
	@Autowired private TripPricer tripPricer;	
	public static final String TRIP_PRICER_KEY = "test-server-api-key";

	public List<Provider> calculateProposals(User user, List<AttractionNearby> attractions, int cumulativeRewardPoints) {
		List<Provider> providers = new ArrayList<Provider>();
		for (AttractionNearby a : attractions) {
			providers.addAll(tripPricer.getPrice(
					TRIP_PRICER_KEY, 
					a.id, 
					user.getUserPreferences().getNumberOfAdults(), 
					user.getUserPreferences().getNumberOfChildren(), 
					user.getUserPreferences().getTripDuration(), 
					cumulativeRewardPoints));			
		}
//		user.setTripDeals(providers); // NOT USED
		return providers;
	}
}