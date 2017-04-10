package healthcare.webservice;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import healthcare.app.doctor.InsertDoctorCommand;
import healthcare.app.doctor.InsertDoctorCommandHandle;
import healthcare.app.login.LoginProcess;
import healthcare.app.login.LoginQuery;
import healthcare.app.login.LoginResult;
import healthcare.domain.doctor.DoctorDto;

@Path("/Doctor")
@Stateless
public class Doctor {
	@Inject
	private InsertDoctorCommandHandle insertdoctor;

	@POST
	@Path("/insert")
	@Produces(MediaType.APPLICATION_JSON)
	public void Insert(InsertDoctorCommand command) {

		insertdoctor.handle(command);

	}
	@POST
	@Path("/getDoctor")
	@Produces(MediaType.APPLICATION_JSON)
	public DoctorDto getAllDoctor() {
		return null;
	}
}
