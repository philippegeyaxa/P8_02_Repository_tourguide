package tripmaster.tourguide.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tripmaster.common.user.User;
import tripmaster.tourguide.user.UserService;
import tripmaster.tourguide.user.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Test
	public void givenUser_whenAddUser_thenUserIsAdded() {
		// GIVEN
		UserService userService = new UserServiceImpl(false);
		User givenUser = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
		// WHEN
		userService.addUser(givenUser);
		User resultUser = userService.getUser(givenUser.userName); 
		// THEN
		assertNotNull(resultUser);
		assertEquals(givenUser.userId, resultUser.userId);
	}
	
	@Test
	public void givenUserList_whenGetUser_thenReturnsRightUser() {
		// GIVEN
		UserService userService = new UserServiceImpl(false);
		User givenUser1 = new User(UUID.randomUUID(), "jon1", "0001", "jon1@tourGuide.com");
		User givenUser2 = new User(UUID.randomUUID(), "jon2", "0002", "jon2@tourGuide.com");
		userService.addUser(givenUser1);
		userService.addUser(givenUser2);
		// WHEN
		User user = userService.getUser(givenUser2.userName);
		// THEN
		assertEquals(givenUser2.userId, user.userId);
		assertEquals(givenUser2.emailAddress, user.emailAddress);
		assertEquals(givenUser2.phoneNumber, user.phoneNumber);
	}	
	
	@Test
	public void givenUserList_whenGetAllUsers_thenReturnsFullList() {
		// GIVEN
		UserService userService = new UserServiceImpl(false);
		User givenUser1 = new User(UUID.randomUUID(), "jon1", "0001", "jon1@tourGuide.com");
		User givenUser2 = new User(UUID.randomUUID(), "jon2", "0002", "jon2@tourGuide.com");
		userService.addUser(givenUser1);
		userService.addUser(givenUser2);
		// WHEN
		List<User> userList = userService.getAllUsers();
		// THEN
		assertNotNull(userList);
		assertEquals(2, userList.size());
		assertTrue(userList.contains(givenUser1));
		assertTrue(userList.contains(givenUser2));
	}
	
	@Test
	public void givenEmptyUserList_whenInitializeInternalUsers_thenGeneratesUserList() {
		// GIVEN
		int numberOfUsers = 10;
		UserService userService = new UserServiceImpl(false);
		List<User> initialUserList = userService.getAllUsers();
		assertNotNull(initialUserList);
		assertEquals(0, initialUserList.size());
		// WHEN
		userService.initializeInternalUsers(numberOfUsers, false);
		List<User> finalUserList = userService.getAllUsers();
		// THEN
		assertNotNull(finalUserList);
		assertEquals(numberOfUsers, finalUserList.size());
	}
}
