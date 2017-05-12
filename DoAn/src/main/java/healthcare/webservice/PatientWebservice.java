package healthcare.webservice;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import healthcare.app.patient.FinderPatient;
import healthcare.app.patient.InsertPatientCommand;
import healthcare.app.patient.InsertPatientCommandHandle;
import healthcare.app.patient.RemovePatientCommandHandle;
import healthcare.domain.patient.PatientDto;
import healthcare.domain.patient.PatientResultInsert;

@Path("/Patient")
@Stateless
public class PatientWebservice {
	@Inject
	private InsertPatientCommandHandle insertPatient;
	@Inject
	private FinderPatient finderPatient;
	@Inject
	private RemovePatientCommandHandle remove;

	@POST
	@Path("/insertPatient")
	@Produces(MediaType.APPLICATION_JSON)
	public PatientResultInsert Insert(InsertPatientCommand command) {

		return insertPatient.handle(command);

	}
	@POST
	@Path("/getAllPatient")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PatientDto> getAllPatient() {
		return finderPatient.getAllPatient();
	}
	@POST
	@Path("/getPatient")
	@Produces(MediaType.APPLICATION_JSON)
	public PatientDto getPatient(String userId) {
		return finderPatient.getPatient(userId);
	}
	@POST
	@Path("/removePatient")
	@Produces(MediaType.TEXT_PLAIN)
	public String removePatient(String userId) {
		return remove.handle(userId);
	}
}
