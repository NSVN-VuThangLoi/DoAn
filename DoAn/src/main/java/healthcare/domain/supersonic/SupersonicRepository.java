package healthcare.domain.supersonic;

import java.util.Date;
import java.util.List;

public interface SupersonicRepository {

	public List<SupersonicDto> getUserId(String userId);
	
	void insertSupersonic(SupersonicDto dto);
	
	void updateSupersonic(SupersonicDto dto);
	
	void removeSupersonic(String userId,String doctorId, Date dayCare);
	
	public List<SupersonicDto> getAllSupersonicNonImage();
	
	public SupersonicDto getSupersonicId(String supersonicId);
	
	public List<SupersonicDto> getDoctorId(String doctorId);
}
