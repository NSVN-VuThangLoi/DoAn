package healthcare.webservice;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;
@javax.ws.rs.ApplicationPath("Demo")
public class ApplicationRest extends Application {

	@Override
	public Set<Class<?>> getClasses() {
	    Set<Class<?>>resources=new HashSet<Class<?>>();
	    addRest(resources);
		return resources;
	}
    private void addRest(Set<Class<?>>resource)	
    {
    	resource.add(healthcare.webservice.Doctor.class);
    	resource.add(healthcare.webservice.PatientWebservice.class);
    	resource.add(healthcare.webservice.LoginWebservice.class);
    	resource.add(healthcare.webservice.XquangWebservice.class);
    	resource.add(healthcare.webservice.DiagnoseWebservice.class);
    	resource.add(healthcare.webservice.BloodTestWebservice.class);
    	resource.add(healthcare.webservice.SupersonicWebservice.class);
    	resource.add(healthcare.webservice.NurseWebservice.class);
    	resource.add(healthcare.webservice.ChangePassword.class);
    	resource.add(healthcare.webservice.HomeWebservice.class);
    }
}
