package healthcare.webservice;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import healthcare.app.bloodtest.FinderBloodTest;
import healthcare.app.common.UserLogin;
import healthcare.app.home.ResultHome;
import healthcare.app.supersonic.FinderSupersonic;
import healthcare.app.xquang.FinderXquang;
import healthcare.domain.bloodtest.BloodTestDto;
import healthcare.domain.supersonic.SupersonicDto;
import healthcare.domain.xquang.XquangDto;

@Path("/home")
public class HomeWebservice {
	@Inject
	private UserLogin userLogin;
	@Inject
	private FinderBloodTest finderBlood;
	@Inject
	private FinderSupersonic finderSup;
	@Inject
	private FinderXquang finderXquang;
	
	@POST
	@Path("/home")
	public ResultHome getCount(){
		ResultHome result = new ResultHome();
		if(userLogin.getIsDoctor()){
			// count blood test follow doctor 
			List<BloodTestDto> bloodTests= finderBlood.getAllfollowDoctor(userLogin.getDoctorId());
			if(bloodTests == null){
				result.setCountBloodTest(0);
			}else{
				result.setCountBloodTest(bloodTests.size());
			}
			// count supersonic follow doctor 
			List<SupersonicDto> supersonicDtos = finderSup.getAllDoctorId(userLogin.getDoctorId());
			if(supersonicDtos == null){
				result.setCountSupersonic(0);
			}else{
				result.setCountSupersonic(supersonicDtos.size());
			}
			// count xquang follow doctor 
			List<XquangDto> xquangDtos = finderXquang.getAllDoctorId(userLogin.getDoctorId());
			if(xquangDtos == null){
				result.setCountXquang(0);
			}else{
				result.setCountXquang(xquangDtos.size());
			}
		}else
		if(userLogin.getIsNurse()){
			// count blood test follow doctor 
						List<BloodTestDto> bloodTests= finderBlood.getAllNonBloodTest();
						if(bloodTests == null){
							result.setCountBloodTest(0);
						}else{
							result.setCountBloodTest(bloodTests.size());
						}
						// count supersonic follow doctor 
						List<SupersonicDto> supersonicDtos = finderSup.getAllNonImage();
						if(supersonicDtos == null){
							result.setCountSupersonic(0);
						}else{
							result.setCountSupersonic(supersonicDtos.size());
						}
						// count xquang follow doctor 
						List<XquangDto> xquangDtos = finderXquang.getAllNonImage();
						if(xquangDtos == null){
							result.setCountXquang(0);
						}else{
							result.setCountXquang(xquangDtos.size());
						}
		}else{
			return null;
		}
		return result;
	}
}
