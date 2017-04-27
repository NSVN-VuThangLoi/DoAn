package healthcare.webservice;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
@javax.ws.rs.ApplicationPath("Demo")
public class LoginConf extends Application {

	@Override
	public Set<Class<?>> getClasses() {
	    Set<Class<?>>resources=new HashSet<Class<?>>();
	    resources.add(MultiPartFeature.class);
	    addRest(resources);
		return resources;
	}
    private void addRest(Set<Class<?>>resource)	
    {
    	resource.add(healthcare.webservice.Doctor.class);
    	resource.add(healthcare.webservice.PatientWebservice.class);
    	resource.add(healthcare.webservice.FileWebservice.class);
    }
}
