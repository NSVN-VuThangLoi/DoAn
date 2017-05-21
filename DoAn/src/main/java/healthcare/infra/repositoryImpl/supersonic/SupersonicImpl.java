package healthcare.infra.repositoryImpl.supersonic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import org.apache.commons.io.FileUtils;

import healthcare.domain.supersonic.SupersonicDto;
import healthcare.domain.supersonic.SupersonicRepository;
import healthcare.infra.entity.login.DataConnection;
import healthcare.infra.entity.supersonic.SupersonicEntity;
@Stateless
public class SupersonicImpl extends DataConnection implements SupersonicRepository {
	private static String FIND;
	private static String FINDUSERID;
	private static String FINDNONIMAGE;
	private static String FINDXQUANGID;
	private static String FINDDOCTORID;
	static{
		StringBuilder query = new StringBuilder();
		query.append(" SELECT t FROM SupersonicEntity t ");
		FIND = query.toString();
		
		query = new StringBuilder();
		query.append(FIND +" WHERE t.userId = :userId");
		FINDUSERID = query.toString();
		
		query = new StringBuilder();
		query.append(FIND +" WHERE t.isImage = 0");
		FINDNONIMAGE = query.toString();
		
		query = new StringBuilder();
		query.append(FIND +" WHERE t.supersonicId = :supersonicId");
		FINDXQUANGID = query.toString();
		
		query = new StringBuilder();
		query.append(FIND +" WHERE t.doctorId = :doctorId AND t.isResult = 0 AND t.isImage = 1");
		FINDDOCTORID = query.toString();
	}
	@Override
	public List<SupersonicDto> getUserId(String userId) {
		List<SupersonicEntity> supersonicEntitys = this.entityManager.createQuery(FINDUSERID, SupersonicEntity.class).setParameter("userId", userId).getResultList();
		if(supersonicEntitys != null){
			List<SupersonicDto> supersonicDtos = new ArrayList<>();
			for(SupersonicEntity entity : supersonicEntitys){
				SupersonicDto supersonicDto = new SupersonicDto();
				supersonicDto.setSupersonicId(entity.getSupersonicId());
				supersonicDto.setVersion(entity.getVersion());
				supersonicDto.setUserId(entity.getUserId());
				supersonicDto.setDoctorId(entity.getDoctorId());
				supersonicDto.setAge(entity.getAge());
				supersonicDto.setDiagnose(entity.getDiagnose());
				supersonicDto.setResult(entity.getResult());
				supersonicDto.setDayCare(entity.getDayCare());
				supersonicDto.setAddressPatient(entity.getAddressPatient());
				supersonicDto.setUrlImage(entity.getUrlImage());
				supersonicDto.setName(entity.getName());
				supersonicDtos.add(supersonicDto);
			}
			return supersonicDtos;
		}
		return null;
	}

	@Override
	public List<SupersonicDto> getDoctorId(String doctorId) {
		List<SupersonicEntity> supersonicEntitys = this.entityManager.createQuery(FINDDOCTORID, SupersonicEntity.class)
				.setParameter("doctorId", doctorId).getResultList();
		if(supersonicEntitys != null){
			List<SupersonicDto> supersonicDtos = new ArrayList<>();
			for(SupersonicEntity entity : supersonicEntitys){
				SupersonicDto dto = new SupersonicDto();
				dto.setSupersonicId(entity.getSupersonicId());
				dto.setAddressPatient(entity.getAddressPatient());
				dto.setUserId(entity.getUserId());
				dto.setDiagnose(entity.getDiagnose());
				dto.setAge(entity.getAge());
				dto.setDayCare(entity.getDayCare());
				dto.setName(entity.getName());
				supersonicDtos.add(dto);
			}
			return supersonicDtos;
		}
		return null;
	}

	@Override
	public void insertSupersonic(SupersonicDto dto) {
		SupersonicEntity entity = new SupersonicEntity();
		entity.setSupersonicId(dto.getSupersonicId());
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
	public void updateSupersonic(SupersonicDto dto) {
		SupersonicEntity entity = new SupersonicEntity();
		entity.setSupersonicId(dto.getSupersonicId());
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
	public void removeSupersonic(String userId, String doctorId, Date dayCare) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SupersonicDto> getAllSupersonicNonImage() {
		List<SupersonicEntity> supersonicEntitys = this.entityManager.createQuery(FINDNONIMAGE, SupersonicEntity.class).getResultList();
		if(supersonicEntitys != null){
			List<SupersonicDto> Dtos = new ArrayList<>();
			for(SupersonicEntity entity : supersonicEntitys){
				SupersonicDto dto = new SupersonicDto();
				dto.setVersion(entity.getVersion());
				dto.setUserId(entity.getUserId());
				dto.setDoctorId(entity.getDoctorId());
				dto.setName(entity.getName());
				dto.setAge(entity.getAge());
				dto.setDiagnose(entity.getDiagnose());
				dto.setResult(entity.getResult());
				dto.setDayCare(entity.getDayCare());
				dto.setAddressPatient(entity.getAddressPatient());
				dto.setUrlImage(entity.getUrlImage());
				dto.setSupersonicId(entity.getSupersonicId());
				Dtos.add(dto);
			}
			return Dtos;
		}
		return null;
	}

	@Override
	public SupersonicDto getSupersonicId(String supersonicId) {
		TypedQuery<SupersonicEntity> query = this.entityManager.createQuery(FINDXQUANGID, SupersonicEntity.class).setParameter("supersonicId", supersonicId);
		if(query.getResultList().size() > 0){
			SupersonicEntity supersonicEntity = query.getResultList().get(0);
			SupersonicDto dto = new SupersonicDto();
			dto.setSupersonicId(supersonicEntity.getSupersonicId());
			dto.setUserId(supersonicEntity.getUserId());
			dto.setDoctorId(supersonicEntity.getDoctorId());
			dto.setDayCare(supersonicEntity.getDayCare());
			dto.setAddressPatient(supersonicEntity.getAddressPatient());
			dto.setAge(supersonicEntity.getAge());
			dto.setDiagnose(supersonicEntity.getDiagnose());
//			dto.setUrlImage(supersonicEntity.getUrlImage());
			dto.setResult(supersonicEntity.getResult());
			dto.setIsResult(supersonicEntity.getIsResult());
			if(supersonicEntity.getUrlImage() != null){
				File file = new File(supersonicEntity.getUrlImage());
			     try {
			    	 FileUtils.readFileToByteArray(file);
			    	 dto.setUrlImage("data:image/gif;base64,"+Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(file)));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
			}
			dto.setIsImage(supersonicEntity.getIsImage());
			dto.setVersion(supersonicEntity.getVersion());
			dto.setName(supersonicEntity.getName());
			return dto;
		}
		return null;
	}
}
