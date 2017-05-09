package healthcare.app.bloodtest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import healthcare.domain.bloodtest.BloodTestDto;
import healthcare.domain.bloodtest.BloodTestRepository;

@Stateless
public class FinderBloodTest {
	@Inject
	private BloodTestRepository bloodTestRep;
	public List<BloodTestDto> getAllNonBloodTest(){
		return bloodTestRep.getNonvalueBloodTest();
	}
	public BloodTestDto getBloodTest(String bloodTestId){
		return bloodTestRep.getBloodTestId(bloodTestId);
	}
}
