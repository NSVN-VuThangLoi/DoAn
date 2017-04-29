package healthcare.infra.repositoryImpl.doctor;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import healthcare.domain.doctor.DoctorDto;
import healthcare.domain.doctor.DoctorRepository;
import healthcare.infra.entity.doctor.DoctorEntity;
import healthcare.infra.entity.login.DataConnection;
@Stateless
public class DoctorImpl extends DataConnection implements DoctorRepository{
	private static String FINDALL;
	private static String FINDUSERID;
	static {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append(" SELECT t FROM DoctorEntity t");
		FINDALL = queryBuilder.toString();
		
		queryBuilder = new StringBuilder();
		queryBuilder.append(FINDALL);
		queryBuilder.append(" WHERE t.doctorId = :doctorId");
		FINDUSERID = queryBuilder.toString();
	}


	@Override
	public void insertDoctor(DoctorDto dto) {
		DoctorDto doctorDto = new DoctorDto();
		boolean isInsert = true;
		doctorDto = getDoctor(dto.getDoctorId());
			if(doctorDto != null){
				isInsert = false;
			}	
		
		DoctorEntity entity = new DoctorEntity();
		if(isInsert){
			entity.setAddress(dto.getAddressWord());
			entity.setDoctorId(dto.getDoctorId());
			entity.setName(dto.getName());
			entity.setBirthDay(dto.getBirthDay());
			entity.setEmail(dto.getEmail());
			entity.setPassword(dto.getPassword());
			entity.setPosition(dto.getPosition());
			entity.setPhoneNumber(dto.getPhoneNumber());
			entity.setSex(dto.getSex());
		}else{
			entity.setAddress(dto.getAddressWord());
			entity.setDoctorId(dto.getDoctorId());
			entity.setName(dto.getName());
			entity.setBirthDay(dto.getBirthDay());
			entity.setEmail(dto.getEmail());
			entity.setPassword(dto.getPassword());
			entity.setPosition(dto.getPosition());
			entity.setPhoneNumber(dto.getPhoneNumber());
			entity.setSex(dto.getSex());
			entity.setVersion(dto.getVersion());
		}
		
			
		this.entityManager.merge(entity);
	}

	@Override
	public List<DoctorDto> getAllDoctor() {
		
		List<DoctorEntity> doctorEntitys = this.entityManager.createQuery(FINDALL,DoctorEntity.class).getResultList();
		if(doctorEntitys != null){
			List<DoctorDto> doctorDtos = new ArrayList<>();
			for(DoctorEntity entity : doctorEntitys){
				DoctorDto doctor = new DoctorDto();
				doctor.setAddressWord(entity.getAddress());
				doctor.setBirthDay(entity.getBirthDay());
				doctor.setDoctorId(entity.getDoctorId());
				doctor.setEmail(entity.getEmail());
				doctor.setName(entity.getName());
				doctor.setPassword(entity.getPassword());
				doctorDtos.add(doctor);
			}
			return doctorDtos;
		}
		return null;
	}

	@Override
	public DoctorDto getDoctor(String userId) {
		
		TypedQuery<DoctorEntity> query = this.entityManager.createQuery(FINDUSERID, DoctorEntity.class).setParameter("doctorId",userId);
		if(query.getResultList().size() > 0){
			DoctorEntity doctorEntity = query.getResultList().get(0);
				DoctorDto dto = new DoctorDto(doctorEntity.getVersion(),
						doctorEntity.getDoctorId(),
						doctorEntity.getName(),
						doctorEntity.getBirthDay(),
						doctorEntity.getPhoneNumber(),
						doctorEntity.getEmail(),
						doctorEntity.getPassword(),
						doctorEntity.getPosition(),
						doctorEntity.getAddress(),
						doctorEntity.getSex());
				
				return dto;
		}
		return null;
	}

	@Override
	public void remove(String doctorId) {
		String REMOVE = "DELETE FROM DoctorEntity e"
                + " WHERE e.doctorId = :doctorId";
        this.entityManager.createQuery(REMOVE).setParameter("doctorId", doctorId).executeUpdate();
		
	}
}
