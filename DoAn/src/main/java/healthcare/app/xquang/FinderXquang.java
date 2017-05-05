package healthcare.app.xquang;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import healthcare.domain.xquang.XquangDto;
import healthcare.domain.xquang.XquangRepository;

@Stateless
public class FinderXquang {
	@Inject
	private XquangRepository xquangRep;
	public List<XquangDto> getAllNonImage(){
		return xquangRep.getAllXquangNonImage();
	}
	public XquangDto getXquangId(String xquangId){
		return xquangRep.getXquangId(xquangId);
	}
	public List<XquangDto> getAllDoctorId(String doctorId){
		return xquangRep.getDoctorId(doctorId);
	}
}
