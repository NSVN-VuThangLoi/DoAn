package healthcare.webservice;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import healthcare.app.bloodtest.InsertBloodTestCommand;
import healthcare.app.bloodtest.InsertBloodTestCommandHandle;
import healthcare.app.common.UserLogin;
import healthcare.app.diagnose.DiagnoseDto;
import healthcare.app.patient.FinderPatient;
import healthcare.app.supersonic.InsertSupersonicCommand;
import healthcare.app.supersonic.InsertSupersonicCommandHandle;
import healthcare.app.xquang.InsertXquangCommand;
import healthcare.app.xquang.InsertXquangCommandHandle;
import healthcare.domain.bloodtest.ResultBloodTest;
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
	@Inject
	private InsertBloodTestCommandHandle bloodTestHandle;
	@Inject
	private InsertSupersonicCommandHandle supersonicHandle;
	
	@POST
	@Path("/insert")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultBloodTest insertDiagnose(DiagnoseDto dto){
		ResultBloodTest result = new ResultBloodTest();
		PatientDto patienDto = find.getPatient(dto.getUserId());
		if(login.getIsDoctor()){
			if(patienDto != null){
				if(dto.getName().equals(patienDto.getName())){
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
						command.setDoctorId(login.getDoctorId());
						command.setDiagnose(dto.getDiagnose());
						command.setName(patienDto.getName());
						result = xQuangHandle.handle(command);
					}
					if(dto.getBloodtest()){
						InsertBloodTestCommand command = new InsertBloodTestCommand();
						command.setUserId(dto.getUserId());
						command.setDayCare(new Date());
						command.setDiagnose(dto.getDiagnose());
						command.setName(patienDto.getName());
						command.setGender(patienDto.getSex());
						command.setAddress(patienDto.getAddress());
						result = bloodTestHandle.handle(command);
					}
					if(dto.getSupersonic()){
						InsertSupersonicCommand command = new InsertSupersonicCommand();
						command.setUserId(dto.getUserId());
						command.setDayCare(new Date());
						command.setAddressPatern(patienDto.getAddress());
						command.setAge(age);
						command.setDiagnose(dto.getDiagnose());
						command.setDoctorId(login.getDoctorId());
						command.setName(patienDto.getName());
						result = supersonicHandle.handle(command);
					}
				}else{
					result.setResult("Bạn nhập sai tên bệnh nhân hoặc sai userId. Vui lòng xem lại!");
				}
			}
		}else{
			result.setResult("Bạn chưa đăng nhập tài khoản bác sĩ. Xin vui lòng đăng nhập!");
		}
		
		return result;
	}
}
