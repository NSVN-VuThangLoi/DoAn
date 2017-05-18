package healthcare.infra.repositoryImpl.xquang;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import org.apache.commons.io.FileUtils;

import healthcare.domain.xquang.XquangDto;
import healthcare.domain.xquang.XquangRepository;
import healthcare.infra.entity.login.DataConnection;
import healthcare.infra.entity.xquang.XquangEntity;
@Stateless
public class XquangImpl extends DataConnection implements XquangRepository {
	private static String FIND;
	private static String FINDUSERID;
	private static String FINDNONIMAGE;
	private static String FINDXQUANGID;
	private static String FINDDOCTORID;
	static{
		StringBuilder query = new StringBuilder();
		query.append(" SELECT t FROM XquangEntity t ");
		FIND = query.toString();
		
		query = new StringBuilder();
		query.append(FIND +" WHERE t.userId = :userId");
		FINDUSERID = query.toString();
		
		query = new StringBuilder();
		query.append(FIND +" WHERE t.isImage = 0");
		FINDNONIMAGE = query.toString();
		
		query = new StringBuilder();
		query.append(FIND +" WHERE t.xquangId = :xquangId");
		FINDXQUANGID = query.toString();
		
		query = new StringBuilder();
		query.append(FIND +" WHERE t.doctorId = :doctorId AND t.isResult = 0 AND t.isImage = 1");
		FINDDOCTORID = query.toString();
	}
	@Override
	public List<XquangDto> getUserId(String userId) {
		List<XquangEntity> xquangEntitys = this.entityManager.createQuery(FINDUSERID, XquangEntity.class).setParameter("userId", userId).getResultList();
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
				xquangDto.setUrlImage(entity.getUrlImage());
				xquangDto.setName(entity.getName());
				xquangDtos.add(xquangDto);
			}
			return xquangDtos;
		}
		return null;
	}

	@Override
	public void insertXquang(XquangDto dto) {
		XquangEntity entity = new XquangEntity();
		entity.setXquangId(dto.getXquangId());
		entity.setUserId(dto.getUserId());
		entity.setDoctorId(dto.getDoctorId());
		entity.setDayCare(dto.getDayCare());
		entity.setAddressPatient(dto.getAddressPatient());
		entity.setIsImage(false);
		entity.setAge(dto.getAge());
		entity.setDiagnose(dto.getDiagnose());
		entity.setIsResult(false);
		entity.setName(dto.getName());
		
		this.entityManager.persist(entity);
	}

	@Override
	public void updateXquang(XquangDto dto) {
		XquangEntity entity = new XquangEntity();
		entity.setXquangId(dto.getXquangId());
		entity.setUserId(dto.getUserId());
		entity.setDoctorId(dto.getDoctorId());
		entity.setDayCare(dto.getDayCare());
		entity.setAddressPatient(dto.getAddressPatient());
		entity.setAge(dto.getAge());
		entity.setDiagnose(dto.getDiagnose());
		entity.setResult(dto.getResult());
		entity.setIsImage(dto.getIsImage());
		entity.setIsResult(dto.getIsResult());
		entity.setVersion(dto.getVersion());
		entity.setUrlImage(dto.getUrlImage());
		entity.setName(dto.getName());
		
		this.entityManager.merge(entity);
		
	}

	@Override
	public void removeXquang(String userId, String doctorId, Date dayCare) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<XquangDto> getAllXquangNonImage() {
		List<XquangEntity> xquangEntitys = this.entityManager.createQuery(FINDNONIMAGE, XquangEntity.class).getResultList();
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
				xquangDto.setUrlImage(entity.getUrlImage());
				xquangDto.setXquangId(entity.getXquangId());
				xquangDtos.add(xquangDto);
			}
			return xquangDtos;
		}
		return null;
	}

	@Override
	public XquangDto getXquangId(String xquangId) {
		TypedQuery<XquangEntity> query = this.entityManager.createQuery(FINDXQUANGID, XquangEntity.class).setParameter("xquangId", xquangId);
		if(query.getResultList().size() > 0){
			XquangEntity xquangEntity = query.getResultList().get(0);
			XquangDto dto = new XquangDto();
			dto.setXquangId(xquangEntity.getXquangId());
			dto.setUserId(xquangEntity.getUserId());
			dto.setDoctorId(xquangEntity.getDoctorId());
			dto.setDayCare(xquangEntity.getDayCare());
			dto.setAddressPatient(xquangEntity.getAddressPatient());
			dto.setAge(xquangEntity.getAge());
			dto.setDiagnose(xquangEntity.getDiagnose());
			dto.setResult(xquangEntity.getResult());
			dto.setIsResult(xquangEntity.getIsResult());
			if(xquangEntity.getUrlImage() != null){
				File file = new File(xquangEntity.getUrlImage());
			     try {
			    	 FileUtils.readFileToByteArray(file);
			    	 dto.setUrlImage("data:image/gif;base64,"+Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(file)));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
			}
			dto.setIsImage(xquangEntity.getIsImage());
			dto.setVersion(xquangEntity.getVersion());
			dto.setName(xquangEntity.getName());
			return dto;
		}
		return null;
	}

	@Override
	public List<XquangDto> getDoctorId(String doctorId) {
		List<XquangEntity> xquangEntitys = this.entityManager.createQuery(FINDDOCTORID, XquangEntity.class)
				.setParameter("doctorId", doctorId).getResultList();
		if(xquangEntitys != null){
			List<XquangDto> xquangDtos = new ArrayList<>();
			for(XquangEntity entity : xquangEntitys){
				XquangDto dto = new XquangDto();
				dto.setXquangId(entity.getXquangId());
				dto.setAddressPatient(entity.getAddressPatient());
				dto.setUserId(entity.getUserId());
				dto.setDiagnose(entity.getDiagnose());
				dto.setAge(entity.getAge());
				dto.setDayCare(entity.getDayCare());
				xquangDtos.add(dto);
			}
			return xquangDtos;
		}
		return null;
	}
}
