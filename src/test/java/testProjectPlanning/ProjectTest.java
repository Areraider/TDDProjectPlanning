package testProjectPlanning;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import projectPlanning.Project;
import projectPlanning.User;

public class ProjectTest {
	@Test
	public void testCreateProject() {
		Project testProject = new Project("Omega", new Date(2021, 1, 1), new Date(2021, 6, 4), 420);
		
		assertEquals("Omega", testProject.getDescription());
		assertEquals((new Date(2021, 1, 1)).toString(), testProject.getStartDate().toString());
		assertEquals((new Date(2021, 6, 4)).toString(), testProject.getEndDate().toString());
		assertEquals(420, testProject.getTotalHours());
		
		testProject.remove();
	}
	
	@Test
	public void testEditProject() {
		Project testProject = new Project("Omega", new Date(2021, 1, 1), new Date(2021, 6, 4), 420);
		testProject.setEndDate(new Date(2021, 12, 12));
		testProject.setHoursTotal(1000);
		
		assertEquals("Omega", testProject.getDescription());
		assertEquals((new Date(2021, 1, 1)).toString(), testProject.getStartDate().toString());
		assertEquals((new Date(2021, 12, 12)).toString(), testProject.getEndDate().toString());
		assertEquals(1000, testProject.getTotalHours());
		
		testProject.remove();
	}
	
	@Test
	public void testAssignUser() {
		User testUser = new User("Udo", "Juergens", "110", "Juergenweg", "5a", 12345);
		Project testProject = new Project("Omega", new Date(2021, 1, 1), new Date(2021, 6, 4), 420);
		testProject.assignUser(testUser, false, false, true, false, 150);
		
		assertTrue(testProject.getAssignedUsers().containsKey(testUser));

		testUser.remove();
		testProject.remove();
	}
	
	@Test
	public void testGetAvailableUsers() {
		User testUser = new User("Udo", "Juergens", "110", "Juergenweg", "5a", 12345);
		Project testProject = new Project("Omega", new Date(2021, 1, 1), new Date(2021, 6, 4), 420);
		
		assertEquals(testUser, testProject.GetAvailableUsers().get(0));

		testUser.remove();
		testProject.remove();
	}
	
	@Test
	public void testFailAssignUser() {
		Project project1 = new Project("Omega", new Date(2021, 6, 4), new Date(2021, 7, 4), 420);
		Project project2 = new Project("Skatepark", new Date(2021, 6, 7), new Date(2021, 8, 4), 420);
		User user = new User();
		project1.assignUser(user, false, true, false, false, 30);
		
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				project2.assignUser(user, false, true, false, false, 30);
			}
		});
		assertEquals("Users can only be in one project at a time!", exception.getMessage());
		
		project1.remove();
		project2.remove();
		user.remove();
	}
	
	@Test
	public void testTrackHours() {
		Project project1 = new Project("Omega", new Date(2021, 6, 4), new Date(2021, 7, 4), 420);
		User user = new User();
		project1.assignUser(user, true, true, true, true, 420);

		project1.trackHours(user, 1);
		project1.trackHours(user, 3);
		
		Map<User, Integer> trackedHours = project1.getTrackedHours();
		assertEquals(4, trackedHours.get(user).intValue());

		project1.remove();
		user.remove();
	}
}
