package tourguide.gps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import tourguide.model.User;

@Service
public class GpsService {

	@Autowired private GpsUtil gpsUtil;

	public VisitedLocation trackUserLocation(User user) {
		VisitedLocation visitedLocation = gpsUtil.getUserLocation(user.getUserId());
		user.addToVisitedLocations(visitedLocation);
		return visitedLocation;
	}

	public void trackAllUserLocations(List<User> userList) {
		userList.stream().forEach(user -> {
			VisitedLocation visitedLocation = gpsUtil.getUserLocation(user.getUserId());
			user.addToVisitedLocations(visitedLocation);
		});
	}

	public VisitedLocation getUserLocation(User user) {
		VisitedLocation visitedLocation = (user.getVisitedLocations().size() > 0) ?
			user.getLastVisitedLocation() :
			trackUserLocation(user);
		return visitedLocation;
	}
	
	public Map<UUID,Location> getAllUserLocations(List<User> userList) {
		// Get visited locations for all of them
		Map<UUID,Location> allUserLocations = new HashMap<UUID,Location>();
		for (User u : userList) {
			allUserLocations.put(u.getUserId(), getUserLocation(u).location);
		}
		return allUserLocations;
	}
	
	public List<Attraction> getAllAttractions() {
		return gpsUtil.getAttractions();
	}
}
