package healthcare.app.supersonic;

import java.text.SimpleDateFormat;

import javax.ejb.Stateless;
import javax.inject.Inject;

import healthcare.domain.bloodtest.ResultBloodTest;
import healthcare.domain.supersonic.SupersonicDto;
import healthcare.domain.supersonic.SupersonicRepository;

@Stateless
public class InsertSupersonicCommandHandle {
	@Inject
	private SupersonicRepository supersonicRep;
	public ResultBloodTest handle(InsertSupersonicCommand command){
		ResultBloodTest result = new ResultBloodTest();
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyyymmddhhmmss");
		String dayCare = formatDate.format(command.getDayCare());
		SupersonicDto dto = new SupersonicDto();
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
		dto.setSupersonicId(dto.getUserId() + dayCare);
		dto.setName(command.getName());
		try {
			supersonicRep.insertSupersonic(dto);
			result.setResult("Insert thành công");
		} catch (Exception e) {
			result.setResult("Insert thất bại");
		}
		
		return result;
	}
}
