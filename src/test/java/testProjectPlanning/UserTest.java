package testProjectPlanning;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.*;

import projectPlanning.Project;
import projectPlanning.User;

public class UserTest {
	
	@Test
	public void testFindUser() {
		User testUser = new User("Angela", "Merkel", "112", "Kanzlerinstraﬂe", "1", 12345);
		assertEquals(testUser, User.findUser("Angela", "Merkel"));
		testUser.remove();
	}
	
	@Test
	public void testGetProjectsForTimespan() {
		User testUser = new User("Angela", "Merkel", "112", "Kanzlerinstraﬂe", "1", 12345);
		Project testProject = new Project("Politik", new Date(2021, 3, 5), new Date(2021, 6, 6), 420);
		testProject.assignUser(testUser, true, true, true, true, 420);
		
		assertEquals(testProject, testUser.getProjectsForTimespan(new Date(2021, 4, 20), new Date(2021, 5, 28)).get(0));

		testUser.remove();
		testProject.remove();
	}
}
