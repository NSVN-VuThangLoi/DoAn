package healthcare.webservice;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;
@javax.ws.rs.ApplicationPath("Demo")
public class LoginConf extends Application {

	@Override
	public Set<Class<?>> getClasses() {
	    Set<Class<?>>resources=new HashSet<Class<?>>();
	    addRest(resources);
		return resources;
	}
    private void addRest(Set<Class<?>>resource)	
    {
    	resource.add(healthcare.webservice.Testlogin.class);
    }
}
