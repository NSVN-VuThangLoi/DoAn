package healthcare.infra.repositoryImpl.xquang;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import healthcare.domain.patient.PatientDto;
import healthcare.domain.xquang.XquangDto;
import healthcare.domain.xquang.XquangRepository;
import healthcare.infra.entity.login.DataConnection;
import healthcare.infra.entity.patient.PatientEntity;
import healthcare.infra.entity.xquang.XquangEntity;
@Stateless
public class XquangImpl extends DataConnection implements XquangRepository {
	private static String FINDUSERID;
	static{
		StringBuilder query = new StringBuilder();
		query.append(" SELECT t FROM XquangEntity t ");
		query.append(" WHERE t.userId = :userId");
		FINDUSERID = query.toString();
	}
	@Override
	public List<XquangDto> getUserId(String userId) {
		List<XquangEntity> xquangEntitys = this.entityManager.createNamedQuery(FINDUSERID, XquangEntity.class).getResultList();
		if(xquangEntitys != null){
			List<XquangDto> xquangDtos = new ArrayList<>();
			for(XquangEntity entity : xquangEntitys){
				XquangDto xquangDto = new XquangDto();
				xquangDto.setVersion(entity.getVersion());
				xquangDto.setUserId(entity.getUserId());
				xquangDto.setDoctorId(entity.getDoctorId());
				xquangDto.setAge(entity.getAge());
				xquangDto.setDiagnose(entity.getDiagnose());
				xquangDto.setResult(entity.getResult());
				xquangDto.setDayCare(entity.getDayCare());
				xquangDto.setAddressPatient(entity.getAddressPatient());
				xquangDto.setImageXquang(entity.getImageXquang());
				xquangDtos.add(xquangDto);
				return xquangDtos;
			}
		}
		return null;
	}

	@Override
	public XquangDto getProFileXquang(String userId, String doctorId, Date dayCare) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertXquang(XquangDto dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateXquang(XquangDto dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeXquang(String userId, String doctorId, Date dayCare) {
		// TODO Auto-generated method stub
		
	}

}
