package healthcare.app.nurse;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import healthcare.domain.nurse.NurseDto;
import healthcare.domain.nurse.NurseRepository;
@Stateless
public class FinderNurse {
	@Inject
	private NurseRepository nurseRep;
	public List<NurseDto> getAllNurse(){
		return nurseRep.getAllNurse();
	}
	public NurseDto getNurse(String nurseId){
		return nurseRep.getNurse(nurseId);
	}

}
