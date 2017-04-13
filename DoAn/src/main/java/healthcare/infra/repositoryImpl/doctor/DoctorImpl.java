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
		
		DoctorEntity entity = new DoctorEntity();
		entity.setAddress(dto.getAddressWord());
		entity.setDoctorId(dto.getDoctorId());
		entity.setName(dto.getName());
		entity.setBirthDay(dto.getBirthDay());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		entity.setPosition(dto.getPosition());
		entity.setPhoneNumber(dto.getPhoneNumber());
		entity.setSex(dto.getSex());
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
		DoctorEntity doctorEntity = query.getSingleResult();
		if(doctorEntity != null){
			DoctorDto dto = new DoctorDto(doctorEntity.getDoctorId(),
					doctorEntity.getName(),
					doctorEntity.getBirthDay(),
					doctorEntity.getPhoneNumber(),
					doctorEntity.getEmail(),
					doctorEntity.getPosition(),
					doctorEntity.getAddress(),
					doctorEntity.getSex());
			return dto;
		}
		return null;
	}
	

}
