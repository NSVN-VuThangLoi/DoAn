package healthcare.domain.bloodtest;

import java.util.List;

public interface BloodTestRepository {
	
	public ResultBloodTest Insert(BloodTestDto dto);
	
	public ResultBloodTest Update(BloodTestDto dto);
	
	public BloodTestDto getBloodTestId(String bloodTestId);
	
	public List<BloodTestDto> getBloodDoctorId(String doctorId);
	
	public List<BloodTestDto> getNonvalueBloodTest();
	
}
