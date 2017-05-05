package healthcare.domain.xquang;

import java.util.Date;
import java.util.List;

public interface XquangRepository {

	public List<XquangDto> getUserId(String userId);
	
	void insertXquang(XquangDto dto);
	
	void updateXquang(XquangDto dto);
	
	void removeXquang(String userId,String doctorId, Date dayCare);
	
	public List<XquangDto> getAllXquangNonImage();
	
	public XquangDto getXquangId(String xquangId);
	
	public List<XquangDto> getDoctorId(String doctorId);
}
