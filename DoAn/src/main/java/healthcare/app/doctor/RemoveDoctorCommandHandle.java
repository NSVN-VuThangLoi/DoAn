package healthcare.app.doctor;

import javax.ejb.Stateless;
import javax.inject.Inject;

import healthcare.domain.doctor.DoctorRepository;
@Stateless
public class RemoveDoctorCommandHandle {
	@Inject
	private DoctorRepository docterRep;
	public String handle(String doctorId){
		String success ="";
		try {
			docterRep.remove(doctorId);
			success =" Bạn đã xóa thành công";
		} catch (Exception e) {
			success ="Xóa bị lỗi";
		}
		
		return success;
	}

}
