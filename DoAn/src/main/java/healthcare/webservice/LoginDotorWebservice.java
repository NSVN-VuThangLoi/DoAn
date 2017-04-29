package healthcare.webservice;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import healthcare.app.login.LoginProcess;
import healthcare.app.login.LoginQuery;
import healthcare.app.login.LoginResult;

@Path("/loginDoctor")
public class LoginDotorWebservice {
	@Inject
	private LoginProcess loginProcess;
	@POST
	@Path("/checkDoctor")
	public LoginResult signIn(LoginQuery query){
		return loginProcess.handle(query);
	}
}
