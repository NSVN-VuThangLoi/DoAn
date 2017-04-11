package healthcare.infra.repositoryImpl.doctor;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

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
		queryBuilder.append("WHERE t.userId =: userId");
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
		List<DoctorDto> doctorDtos = new ArrayList<>();
	
		List<DoctorEntity> doctorEntitys = this.entityManager.createQuery(FINDALL,DoctorEntity.class).getResultList();
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

	@Override
	public DoctorDto getDoctor(String userId) {
		DoctorDto doctorDto = new DoctorDto();
		DoctorEntity doctorEntity = this.entityManager.createQuery(FINDUSERID, DoctorEntity.class).setParameter("userId",userId).getSingleResult();
		return null;
	}
	

}
