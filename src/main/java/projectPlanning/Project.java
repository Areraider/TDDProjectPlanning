package projectPlanning;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Project {
	public static List<Project> allProjects = new LinkedList<Project>();
	
	protected String description;
	protected Date startDate;
	protected Date endDate;
	protected int totalHours;
	protected Map<User, UserInProject> assignedUsers;
	
	public Project(String description, Date startDate, Date endDate, int hoursTotal) {
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalHours = hoursTotal;
		this.assignedUsers = new HashMap<User, UserInProject>();
		
		allProjects.add(this);
	}
	public Project() {
		this.description = "";
		this.startDate = new Date();
		this.endDate = new Date();
		this.totalHours = 0;
		this.assignedUsers = new HashMap<User, UserInProject>();
		
		allProjects.add(this);
	}
	
	protected void finalize() {
		this.remove();
	}
	
	public void remove() {
		allProjects.remove(this);
	}
	
	public void assignUser(User user, boolean productOwner, boolean scrumMaster, boolean backendDeveloper, boolean frontendDeveloper, int plannedHours) throws IllegalArgumentException {
		if (this.GetAvailableUsers().contains(user))
			this.assignedUsers.put(user, new UserInProject(productOwner, scrumMaster, backendDeveloper, frontendDeveloper, plannedHours));
		else
			throw new IllegalArgumentException("Users can only be in one project at a time!");
	}
	
	public List<User> GetAvailableUsers() {
		List<User> availableUsers = new LinkedList<User>();
		for (int i = 0; i < User.allUsers.size(); i++) {
			User currentUser = User.allUsers.get(i);
			availableUsers.add(currentUser);
			for (int j = 0; j < Project.allProjects.size(); j++) {
				Project currentProject = Project.allProjects.get(j);
				if (currentProject == this) continue;
				
				if ((this.startDate.after(currentProject.startDate) && this.startDate.before(currentProject.endDate)) 
						|| (this.endDate.after(currentProject.startDate) && this.endDate.before(currentProject.endDate))
						|| (this.startDate.after(currentProject.startDate) && this.endDate.before(currentProject.endDate))) {
					if (currentProject.assignedUsers.containsKey(currentUser)) {
						availableUsers.remove(currentUser);
					}
				}
			}
		}
		
		return availableUsers;
	}
	
	public void trackHours(User user, int hours) {
		this.assignedUsers.get(user).trackHours(hours);
	}
	
	public Map<User, Integer> getTrackedHours() {
		Map<User, Integer> usersTrackedHours = new HashMap<User, Integer>();
		
		for (User u : this.assignedUsers.keySet()) {
			usersTrackedHours.put(u, this.assignedUsers.get(u).trackedHours);
		}
		
		return usersTrackedHours;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getTotalHours() {
		return totalHours;
	}
	public void setHoursTotal(int hoursTotal) {
		this.totalHours = hoursTotal;
	}
	public String getDescription() {
		return description;
	}
	public Date getStartDate() {
		return startDate;
	}
	public Map<User, UserInProject> getAssignedUsers() {
		return new HashMap<User, UserInProject>(assignedUsers);
	}
}
