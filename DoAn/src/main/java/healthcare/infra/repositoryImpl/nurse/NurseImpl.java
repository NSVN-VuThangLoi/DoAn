package healthcare.infra.repositoryImpl.nurse;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import healthcare.domain.nurse.NurseDto;
import healthcare.domain.nurse.NurseRepository;
import healthcare.infra.entity.login.DataConnection;
import healthcare.infra.entity.nurse.NurseEntity;
@Stateless
public class NurseImpl extends DataConnection implements NurseRepository{
	private static String FINDALL;
	private static String FINDUSERID;
	static {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append(" SELECT t FROM NurseEntity t");
		FINDALL = queryBuilder.toString();
		
		queryBuilder = new StringBuilder();
		queryBuilder.append(FINDALL);
		queryBuilder.append(" WHERE t.nurseId = :nurseId");
		FINDUSERID = queryBuilder.toString();
	}

	@Override
	public void remove(String nurseId) {
		String REMOVE = "DELETE FROM NurseEntity e"
                + " WHERE e.nurseId = :nurseId";
        this.entityManager.createQuery(REMOVE).setParameter("nurseId", nurseId).executeUpdate();
		
	}

	@Override
	public void insertNurse(NurseDto dto) {
		NurseEntity entity = new NurseEntity();
		entity.setAddress(dto.getAddressWord());
		entity.setNurseId(dto.getNurseId());
		entity.setName(dto.getName());
		entity.setBirthDay(dto.getBirthDay());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		entity.setPosition(dto.getPosition());
		entity.setPhoneNumber(dto.getPhoneNumber());
		entity.setSex(dto.getSex());
		this.entityManager.persist(entity);
	}

	@Override
	public List<NurseDto> getAllNurse() {
		List<NurseEntity> nurseEntitys = this.entityManager.createQuery(FINDALL,NurseEntity.class).getResultList();
		if(nurseEntitys != null){
			List<NurseDto> nurseDtos = new ArrayList<>();
			for(NurseEntity entity : nurseEntitys){
				NurseDto nurseDto = new NurseDto();
				nurseDto.setAddressWord(entity.getAddress());
				nurseDto.setBirthDay(entity.getBirthDay());
				nurseDto.setNurseId(entity.getNurseId());
				nurseDto.setEmail(entity.getEmail());
				nurseDto.setName(entity.getName());
				nurseDto.setPassword(entity.getPassword());
				nurseDtos.add(nurseDto);
			}
			return nurseDtos;
		}
		return null;
	}

	@Override
	public NurseDto getNurse(String nurseId) {
		TypedQuery<NurseEntity> query = this.entityManager.createQuery(FINDUSERID, NurseEntity.class).setParameter("nurseId",nurseId);
		if(query.getResultList().size() > 0){
			NurseEntity nurseEntity = query.getResultList().get(0);
			NurseDto dto = new NurseDto(nurseEntity.getVersion(),
						nurseEntity.getNurseId(),
						nurseEntity.getName(),
						nurseEntity.getBirthDay(),
						nurseEntity.getPhoneNumber(),
						nurseEntity.getEmail(),
						nurseEntity.getPassword(),
						nurseEntity.getPosition(),
						nurseEntity.getAddress(),
						nurseEntity.getSex());
				
				return dto;
		}
		return null;
	}
	@Override
	public void updateNurse(NurseDto dto) {
		NurseEntity entity = new NurseEntity();
		entity.setAddress(dto.getAddressWord());
		entity.setNurseId(dto.getNurseId());
		entity.setName(dto.getName());
		entity.setBirthDay(dto.getBirthDay());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		entity.setPosition(dto.getPosition());
		entity.setPhoneNumber(dto.getPhoneNumber());
		entity.setSex(dto.getSex());
		entity.setVersion(dto.getVersion());
		this.entityManager.merge(entity);
		
	}
}
