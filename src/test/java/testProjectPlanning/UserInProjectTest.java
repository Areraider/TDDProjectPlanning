package testProjectPlanning;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.*;

import projectPlanning.Project;
import projectPlanning.User;

public class UserInProjectTest {
	@Test
	public void testUserInProject() {
		User testUser = new User("Tony", "Hawk", "1234/420420", "Sprungschanzenstraﬂe", "15", 12345);
		Project testProject = new Project("Skatepark", new Date(2021, 6, 4), new Date(2021, 8, 4), 420);
		testProject.assignUser(testUser, true, false, true, true, 400 );

		assertTrue(testProject.getAssignedUsers().get(testUser).isProductOwner());
		assertTrue(testProject.getAssignedUsers().get(testUser).isBackendDeveloper());
		assertTrue(testProject.getAssignedUsers().get(testUser).isFrontendDeveloper());
		
		assertFalse(testProject.getAssignedUsers().get(testUser).isScrumMaster());
		
		assertEquals(400, testProject.getAssignedUsers().get(testUser).getPlannedHours());
		assertEquals(0, testProject.getAssignedUsers().get(testUser).getTrackedHours());
		
		testProject.trackHours(testUser, 20);
		assertEquals(20, testProject.getAssignedUsers().get(testUser).getTrackedHours());

		testUser.remove();
		testProject.remove();
	}
}
