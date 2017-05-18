package healthcare.app.nurse;

import javax.ejb.Stateless;
import javax.inject.Inject;

import healthcare.domain.nurse.NurseDto;
import healthcare.domain.nurse.NurseRepository;
import healthcare.domain.nurse.ResultInsert;

@Stateless
public class InsertNurseCommandHandle {
	@Inject
	private NurseRepository nurseRep;
	
	public ResultInsert handle(InsertNurseCommand command){
		ResultInsert result = new ResultInsert();
		NurseDto dto = new NurseDto();
		if(command.getNurseId() != null){
			NurseDto nurseDto = nurseRep.getNurse(command.getNurseId());
			if(nurseDto == null){
			try {
				dto.setNurseId(command.getNurseId());
				dto.setBirthDay(command.getBirthDay());
				dto.setEmail(command.getEmail());
				dto.setName(command.getName());
				dto.setPassword(command.getPassword());
				dto.setAddressWord(command.getAddressWord());
				dto.setPhoneNumber(command.getPhoneNumber());
				dto.setPosition(command.getPosition());
				dto.setSex(command.getSex());
				nurseRep.insertNurse(dto);
				result.setResult("Đăng kí thành công NurseId = " + command.getNurseId());
				result.setNurseId(command.getNurseId());
			} catch (Exception e) {
				// TODO: handle exception
				result.setResult("Đăng kí thất bại NurseId = " + command.getNurseId());
				result.setNurseId(command.getNurseId());
			}
		}else{
			result.setResult("Mã y tá bạn vừa đăng ký đã có. Bạn vui lòng nhập mã y tá khác!");
		}
		}
		return result;
	}

}
