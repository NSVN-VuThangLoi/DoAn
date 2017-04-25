package healthcare.domain.xquang;

import java.util.Date;
import java.util.List;

public interface XquangRepository {

	public List<XquangDto> getUserId(String userId);
	
	public XquangDto getProFileXquang(String userId, String doctorId,Date dayCare);
	
	void insertXquang(XquangDto dto);
	
	void updateXquang(XquangDto dto);
	
	void removeXquang(String userId,String doctorId, Date dayCare);
}
