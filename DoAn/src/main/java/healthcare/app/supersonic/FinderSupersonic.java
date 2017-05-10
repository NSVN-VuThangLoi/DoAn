package healthcare.app.supersonic;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import healthcare.domain.supersonic.SupersonicDto;
import healthcare.domain.supersonic.SupersonicRepository;

@Stateless
public class FinderSupersonic {
	@Inject
	private SupersonicRepository supersonicRep;
	public List<SupersonicDto> getAllNonImage(){
		return supersonicRep.getAllSupersonicNonImage();
	}
	public SupersonicDto getSupersonicId(String supersonicId){
		return supersonicRep.getSupersonicId(supersonicId);
	}
	public List<SupersonicDto> getAllDoctorId(String doctorId){
		return supersonicRep.getDoctorId(doctorId);
	}
	public List<SupersonicDto> getUserId(String userId){
		return supersonicRep.getUserId(userId);
	}
}
