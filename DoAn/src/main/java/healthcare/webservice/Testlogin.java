package healthcare.webservice;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import healthcare.app.login.LoginProcess;
import healthcare.app.login.LoginQuery;
import healthcare.app.login.LoginResult;

@Path("/Login")
@Stateless
public class Testlogin {
	@Inject
	private LoginProcess loginProcessor;

	// Kiem tra dang nhap
	@POST
	@Path("/insert")
	@Produces(MediaType.APPLICATION_JSON)
	public LoginResult Login(LoginQuery query) {

		return loginProcessor.handle(query);

	}

}
