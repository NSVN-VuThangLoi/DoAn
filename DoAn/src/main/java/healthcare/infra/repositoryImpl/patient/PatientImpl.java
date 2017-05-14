package healthcare.infra.repositoryImpl.patient;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import healthcare.domain.patient.PatientDto;
import healthcare.domain.patient.PatientRepository;
import healthcare.infra.entity.login.DataConnection;
import healthcare.infra.entity.patient.PatientEntity;

@Stateless
public class PatientImpl extends DataConnection implements PatientRepository {
	private static String FINDALL;
	private static String FINDUSERID;
	static {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append(" SELECT t FROM PatientEntity t");
		FINDALL = queryBuilder.toString();
		
		queryBuilder = new StringBuilder();
		queryBuilder.append(FINDALL);
		queryBuilder.append(" WHERE t.userId = :userId");
		FINDUSERID = queryBuilder.toString();
	}
	@Override
	public void insertPatient(PatientDto dto) {
		PatientEntity entity = new PatientEntity();
			entity.setAddress(dto.getAddress());
			entity.setUserId(dto.getUserId());
			entity.setName(dto.getName());
			entity.setBirthDay(dto.getBirthDay());
			entity.setEmail(dto.getEmail());
			entity.setPassword(dto.getPassword());
			entity.setPhoneNumber(dto.getPhoneNumber());
			entity.setSex(dto.getSex());
		this.entityManager.persist(entity);
		
	}

	@Override
	public List<PatientDto> getAllPatient() {
		List<PatientEntity> patientEntitys = this.entityManager.createQuery(FINDALL,PatientEntity.class).getResultList();
		if(patientEntitys != null){
			List<PatientDto> patientDtos = new ArrayList<>();
			for(PatientEntity entity : patientEntitys){
				PatientDto patient = new PatientDto();
				patient.setAddress(entity.getAddress());
				patient.setBirthDay(entity.getBirthDay());
				patient.setUserId(entity.getUserId());
				patient.setEmail(entity.getEmail());
				patient.setName(entity.getName());
				patient.setPassword(entity.getPassword());
				patientDtos.add(patient);
			}
			return patientDtos;
		}
		return null;
	}

	@Override
	public PatientDto getPatient(String userId) {
		TypedQuery<PatientEntity> query = this.entityManager.createQuery(FINDUSERID, PatientEntity.class).setParameter("userId",userId);
		if(query.getResultList().size() > 0){
			PatientEntity patientEntity = query.getResultList().get(0);
			PatientDto dto = new PatientDto(patientEntity.getVersion(),
						patientEntity.getUserId(),
						patientEntity.getName(),
						patientEntity.getBirthDay(),
						patientEntity.getPhoneNumber(),
						patientEntity.getEmail(),
						patientEntity.getAddress(),
						patientEntity.getSex(),
						patientEntity.getPassword());
				return dto;
		}
		return null;
	}

	@Override
	public void remove(String userId) {
		String REMOVE = "DELETE FROM PatientEntity e"
                + " WHERE e.userId = :userId";
        this.entityManager.createQuery(REMOVE).setParameter("userId", userId).executeUpdate();
		
	}

	@Override
	public void updatePatient(PatientDto dto) {
		PatientEntity entity = new PatientEntity();
		entity.setAddress(dto.getAddress());
		entity.setUserId(dto.getUserId());
		entity.setName(dto.getName());
		entity.setBirthDay(dto.getBirthDay());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		entity.setPhoneNumber(dto.getPhoneNumber());
		entity.setSex(dto.getSex());
		entity.setVersion(dto.getVersion());
	this.entityManager.merge(entity);
		
	}

}
