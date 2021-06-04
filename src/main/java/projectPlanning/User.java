package projectPlanning;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class User {
	public static List<User> allUsers = new LinkedList<User>();
	
	protected String firstName;
	protected String lastName;
	protected String phone;
	protected String street;
	protected String houseNumber;
	protected Integer zipCode;

	public User(String firstName, String lastName, String phone, String street, String houseNumber, Integer zipCode) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.street = street;
		this.houseNumber = houseNumber;
		this.zipCode = zipCode;
		
		allUsers.add(this);
	}
	
	public User() {
		this.firstName = "";
		this.lastName = "";
		this.phone = "";
		this.street = "";
		this.houseNumber = "";
		this.zipCode = 0;
		
		allUsers.add(this);
	}
	
	protected void finalize() {
		this.remove();
	}
	
	public void remove() {
		allUsers.remove(this);
	}
	
	public static User findUser(String firstName, String lastName) {
		for (int i = 0; i < allUsers.size(); i++) {
			if (allUsers.get(i).getFirstName().equals(firstName) && allUsers.get(i).getLastName().equals(lastName)) {
				return allUsers.get(i);
			}
		}
		return null;
	}
	
	public List<Project> getProjectsForTimespan(Date startDate, Date endDate) {
		List<Project> projectsForTimespan = new LinkedList<Project>();
		for (int i = 0; i < Project.allProjects.size(); i++) {
			Project currentProject = Project.allProjects.get(i);
			if ((startDate.after(currentProject.startDate) && startDate.before(currentProject.endDate)) 
					|| (endDate.after(currentProject.startDate) && endDate.before(currentProject.endDate))
					|| (startDate.before(currentProject.startDate) && endDate.after(currentProject.endDate))) {
				if (!projectsForTimespan.contains(currentProject) && currentProject.getAssignedUsers().containsKey(this)) {
					projectsForTimespan.add(currentProject);
				}
			}
		}
		return projectsForTimespan;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getTelephoneNumber() {
		return phone;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.phone = telephoneNumber;
	}
	public String getStreetName() {
		return street;
	}
	public void setStreetName(String streetName) {
		this.street = streetName;
	}
	public String getStreetNumber() {
		return houseNumber;
	}
	public void setStreetNumber(String streetNumber) {
		this.houseNumber = streetNumber;
	}
	public Integer getZipCode() {
		return zipCode;
	}
	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}
}
