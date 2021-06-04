package projectPlanning;

public class UserInProject {
	protected boolean productOwner;
	protected boolean scrumMaster;
	protected boolean backendDeveloper;
	protected boolean frontendDeveloper;
	protected int plannedHours;
	protected int trackedHours;
	
	public UserInProject(boolean productOwner, boolean scrumMaster, boolean backendDeveloper, boolean frontendDeveloper, int plannedHours) {
		this.productOwner = productOwner;
		this.scrumMaster = scrumMaster;
		this.backendDeveloper = backendDeveloper;
		this.frontendDeveloper = frontendDeveloper;
		this.plannedHours = plannedHours;
		this.trackedHours = 0;
	}

	public boolean isProductOwner() {
		return productOwner;
	}
	public void setProductOwner(boolean productOwner) {
		this.productOwner = productOwner;
	}

	public boolean isScrumMaster() {
		return scrumMaster;
	}
	public void setScrumMaster(boolean scrumMaster) {
		this.scrumMaster = scrumMaster;
	}

	public boolean isBackendDeveloper() {
		return backendDeveloper;
	}
	public void setBackendDeveloper(boolean backendDeveloper) {
		this.backendDeveloper = backendDeveloper;
	}

	public boolean isFrontendDeveloper() {
		return frontendDeveloper;
	}
	public void setFrontendDeveloper(boolean frontendDeveloper) {
		this.frontendDeveloper = frontendDeveloper;
	}

	public int getPlannedHours() {
		return plannedHours;
	}
	public void setPlannedHours(int plannedHours) {
		this.plannedHours = plannedHours;
	}

	public int getTrackedHours() {
		return trackedHours;
	}
	public void setTrackedHours(int trackedHours) {
		this.trackedHours = trackedHours;
	}
	public void trackHours(int trackedHours) {
		this.trackedHours += trackedHours;
	}
}
