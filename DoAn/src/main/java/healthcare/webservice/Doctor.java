package healthcare.webservice;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import healthcare.app.doctor.FinderDoctor;
import healthcare.app.doctor.InsertDoctorCommand;
import healthcare.app.doctor.InsertDoctorCommandHandle;
import healthcare.domain.doctor.DoctorDto;

@Path("/Doctor")
@Stateless
public class Doctor {
	@Inject
	private InsertDoctorCommandHandle insertdoctor;
	@Inject
	private FinderDoctor finderDoctor;

	@POST
	@Path("/insert")
	@Produces(MediaType.APPLICATION_JSON)
	public void Insert(InsertDoctorCommand command) {

		insertdoctor.handle(command);

	}
	@POST
	@Path("/getAllDoctor")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DoctorDto> getAllDoctor() {
		return finderDoctor.getAllDoctor();
	}
	@POST
	@Path("/getDoctor")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DoctorDto> getDoctor() {
		return finderDoctor.getAllDoctor();
	}
}
