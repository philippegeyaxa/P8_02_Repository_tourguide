package tourguide.gps;

import java.util.List;

import gpsUtil.location.VisitedLocation;
import tourguide.model.AttractionData;
import tourguide.model.User;
import tourguide.model.VisitedLocationData;

public interface GpsService {

	List<User> trackAllUserLocations(List<User> userList);

	VisitedLocationData getCurrentUserLocation(String userIdString);

	List<AttractionData> getAllAttractions();

	VisitedLocationData newVisitedLocationData(VisitedLocation visitedLocation);

}