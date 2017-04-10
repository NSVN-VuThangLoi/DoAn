package healthcare.infra.repositoryImpl.doctor;

import java.util.List;

import javax.ejb.Stateless;

import healthcare.domain.doctor.DoctorDto;
import healthcare.domain.doctor.DoctorRepository;
import healthcare.infra.entity.doctor.DoctorEntity;
import healthcare.infra.entity.login.DataConnection;
@Stateless
public class DoctorImpl extends DataConnection implements DoctorRepository{
	private static String FINDALL;
	static {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append(" SELECT t FROM DoctorEntity t");
		FINDALL = queryBuilder.toString();
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
		List<DoctorDto> doctorDto = this.entityManager.createQuery(FINDALL,DoctorDto.class).getResultList();
		return doctorDto;
	}
	

}
