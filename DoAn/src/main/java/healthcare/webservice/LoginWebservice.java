package healthcare.webservice;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import healthcare.app.login.LoginProcess;
import healthcare.app.login.LoginQuery;
import healthcare.app.login.LoginResult;

@Path("/loginDoctor")
public class LoginWebservice {
	@Inject
	private LoginProcess loginProcess;
	@POST
	@Path("/checkDoctor")
	public LoginResult signIn(LoginQuery query){
		return loginProcess.handle(query);
		
	}
	@POST
	@Path("/checkDoctor")
	public LoginResult signInPatient(LoginQuery query){
		return loginProcess.loginPatient(query);
		
	}
}
