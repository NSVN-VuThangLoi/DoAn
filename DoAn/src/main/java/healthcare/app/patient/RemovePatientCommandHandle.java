package healthcare.app.patient;

import javax.ejb.Stateless;
import javax.inject.Inject;

import healthcare.domain.patient.PatientRepository;
@Stateless
public class RemovePatientCommandHandle {
	@Inject
	private PatientRepository patientRep;
	public String handle(String userId){
		String success ="";
		try {
			patientRep.remove(userId);
			success =" Bạn đã xóa thành công";
		} catch (Exception e) {
			success ="Xóa bị lỗi";
		}
		
		return success;
	}

}
