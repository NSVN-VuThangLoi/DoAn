package healthcare.app.xquang;

import java.text.SimpleDateFormat;

import javax.ejb.Stateless;
import javax.inject.Inject;

import healthcare.domain.xquang.XquangDto;
import healthcare.domain.xquang.XquangRepository;

@Stateless
public class InsertXquangCommandHandle {
	@Inject
	private XquangRepository xQuangrep;
	public String handle(InsertXquangCommand command){
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyyymmddhhmmss");
		String dayCare = formatDate.format(command.getDayCare());
		XquangDto dto = new XquangDto();
		dto.setUserId(command.getUserId());
		dto.setDoctorId(command.getDoctorId());
		dto.setDiagnose(command.getDiagnose());
		dto.setAddressPatient(command.getAddressPatern());
		dto.setDayCare(command.getDayCare());
		dto.setIsResult(false);
		dto.setUrlImage(null);
		dto.setAge(command.getAge());
		dto.setIsImage(false);
		dto.setResult(null);
		dto.setXquangId(dto.getUserId() + dayCare );
		dto.setName(command.getName());
		try {
			xQuangrep.insertXquang(dto);
		} catch (Exception e) {
			return "error";
		}
		
		return null;
	}
}
