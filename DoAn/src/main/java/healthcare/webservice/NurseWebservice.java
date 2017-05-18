package healthcare.webservice;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import healthcare.app.nurse.FinderNurse;
import healthcare.app.nurse.InsertNurseCommand;
import healthcare.app.nurse.InsertNurseCommandHandle;
import healthcare.app.nurse.RemoveNurseCommandHandle;
import healthcare.domain.nurse.NurseDto;
import healthcare.domain.nurse.ResultInsert;

@Path("/Nurse")
@Stateless
public class NurseWebservice {
	@Inject
	private InsertNurseCommandHandle insertNurse;
	@Inject
	private FinderNurse finderNurse;
	@Inject
	private RemoveNurseCommandHandle removeNurse;

	@POST
	@Path("/insert")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultInsert Insert(InsertNurseCommand command) {

		return insertNurse.handle(command);

	}
	@POST
	@Path("/getAllNurse")
	@Produces(MediaType.APPLICATION_JSON)
	public List<NurseDto> getAllNurse() {
		return finderNurse.getAllNurse();
	}
	@POST
	@Path("/getNurse")
	public NurseDto getNurse(String nurseId) {
		return finderNurse.getNurse(nurseId);
	}
	@POST
	@Path("/removeNurse")
	@Produces(MediaType.TEXT_PLAIN)
	public String removeNurse(String nurseId) {
		return removeNurse.handle(nurseId);
	}
}
