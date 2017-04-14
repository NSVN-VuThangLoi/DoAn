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
import healthcare.app.doctor.RemoveDoctorCommandHandle;
import healthcare.domain.doctor.DoctorDto;
import healthcare.domain.doctor.ResultInsert;

@Path("/Doctor")
@Stateless
public class Doctor {
	@Inject
	private InsertDoctorCommandHandle insertdoctor;
	@Inject
	private FinderDoctor finderDoctor;
	@Inject
	private RemoveDoctorCommandHandle removeDoctor;

	@POST
	@Path("/insert")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultInsert Insert(InsertDoctorCommand command) {

		return insertdoctor.handle(command);

	}
	@POST
	@Path("/getAllDoctor")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DoctorDto> getAllDoctor() {
		return finderDoctor.getAllDoctor();
	}
	@POST
	@Path("/getDoctor")
	public DoctorDto getDoctor(String userId) {
		return finderDoctor.getDoctor(userId);
	}
	@POST
	@Path("/removeDoctor")
	@Produces(MediaType.TEXT_PLAIN)
	public String removeDoctor(String doctorId) {
		return removeDoctor.handle(doctorId);
	}
}
