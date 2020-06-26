package tourguide.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import tourguide.user.User;
import tourguide.user.UserPreferences;

@Service
public class TestHelperService {

	@Autowired GpsUtil gpsUtil;
	@Autowired UserService userService;

	public User mockUserServiceGetUserAndGpsUtilGetUserLocation(int index, UserPreferences userPreferences) {
		User user = new User(new UUID(11*index,12*index), "name"+index, "phone"+index, "email"+index);
		Location location = new Location(0.21*index,-0.22*index);
		VisitedLocation visitedLocation = new VisitedLocation(user.getUserId(), location, new Date(index));
		user.addToVisitedLocations(visitedLocation);
		user.setUserPreferences(userPreferences);
		when(userService.getUser(user.getUserName())).thenReturn(user);
		when(gpsUtil.getUserLocation(user.getUserId())).thenReturn(visitedLocation);
		return user;
	}
	
	public List<Attraction> mockGpsUtilGetAttractions() {
		List<Attraction> givenAttractions = new ArrayList<Attraction>();	
		int numberTestCases = TourGuideService.NUMBER_OF_PROPOSED_ATTRACTIONS*2;
		for (int i=0; i<numberTestCases; i++) {
			int index = numberTestCases - i;
			Attraction attraction = new Attraction("name"+index, "city"+index, "state"+index, 0.31*index,-0.32*index);
			givenAttractions.add(attraction);
		}
		when(gpsUtil.getAttractions()).thenReturn(givenAttractions);
		return givenAttractions;
	}
	
	public void deactivateInternalUsers() {
		doNothing().when(userService).initializeInternalUsers();
	}
}