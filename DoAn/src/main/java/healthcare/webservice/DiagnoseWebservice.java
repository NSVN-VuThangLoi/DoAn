package healthcare.webservice;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import healthcare.app.common.UserLogin;
import healthcare.app.diagnose.DiagnoseDto;
import healthcare.app.patient.FinderPatient;
import healthcare.app.xquang.InsertXquangCommand;
import healthcare.app.xquang.InsertXquangCommandHandle;
import healthcare.domain.patient.PatientDto;

@Path("/diagnose")
@Stateless
public class DiagnoseWebservice {
	@Inject
	private InsertXquangCommandHandle xQuangHandle;
	@Inject
	private FinderPatient find;
	@Inject 
	private UserLogin login;
	
	
	@POST
	@Path("/insert")
	@Produces(MediaType.APPLICATION_JSON)
	public String insertDiagnose(DiagnoseDto dto){
		PatientDto patienDto = find.getPatient(dto.getUserId());
		if(patienDto != null){
			@SuppressWarnings("deprecation")
			Date dateCurrent = new Date();
			int yearCurrent = dateCurrent.getYear();
			int yearBirthday = patienDto.getBirthDay().getYear();
			String age =  String.valueOf(yearCurrent - yearBirthday);
			if(dto.getTakeXQuang()){
				InsertXquangCommand command = new InsertXquangCommand();
				command.setAddressPatern(patienDto.getAddress());
				command.setAge(age);
				command.setDayCare(new Date());
				command.setUserId(dto.getUserId());
				command.setDoctorId(login.getUserId());
				command.setDiagnose(dto.getDiagnose());
				xQuangHandle.handle(command);
				return "thanh cong"; 
			}
		}
		return null;
	}
}
