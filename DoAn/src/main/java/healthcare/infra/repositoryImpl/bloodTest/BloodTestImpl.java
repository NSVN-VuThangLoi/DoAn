package healthcare.infra.repositoryImpl.bloodTest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import healthcare.domain.bloodtest.BloodTestDto;
import healthcare.domain.bloodtest.BloodTestRepository;
import healthcare.domain.bloodtest.ResultBloodTest;
import healthcare.infra.entity.bloodtest.BloodTestEntity;
import healthcare.infra.entity.login.DataConnection;
@Stateless
public class BloodTestImpl extends DataConnection implements BloodTestRepository{
	private static String FIND;
	private static String FIND_BLOOD_TESTID;
	private static String FIND_ALL_FOLLOW_DOCTORID;
	private static String FIND_ALL_NON_BLOOD_TEST;
	private static String FINDUSERID;
	static{
		StringBuilder query = new StringBuilder();
		query.append(" SELECT b FROM BloodTestEntity b ");
		FIND = query.toString();
		
		query = new StringBuilder();
		query.append(FIND + " WHERE b.bloodtestId = :bloodtestId");
		FIND_BLOOD_TESTID = query.toString();
		
		query = new StringBuilder();
		query.append(FIND + " WHERE b.doctorId = :doctorId AND b.isResult = 0 AND b.isBloodTest = 1" );
		FIND_ALL_FOLLOW_DOCTORID = query.toString();
		
		query = new StringBuilder();
		query.append(FIND +" WHERE b.isBloodTest = 0");
		FIND_ALL_NON_BLOOD_TEST = query.toString();
		
		query = new StringBuilder();
		query.append(FIND +" WHERE b.userId = :userId");
		FINDUSERID = query.toString();
		
	}

	@Override
	public ResultBloodTest Insert(BloodTestDto dto) {
		ResultBloodTest result = new ResultBloodTest();
		try {
			BloodTestEntity entity = new BloodTestEntity();
			entity.setBloodtestId(dto.getBloodtestId());
			entity.setUserId(dto.getUserId());
			entity.setDoctorId(dto.getDoctorId());
			entity.setDayCare(dto.getDayCare());
			entity.setGender(dto.getGender());
			entity.setIsResult(false);
			entity.setIsBloodTest(false);
			entity.setDiagnose(dto.getDiagnose());
			entity.setName(dto.getName());
			entity.setAddress(dto.getAddress());
			this.entityManager.persist(entity);
			result.setResult("Hồ sơ chẩn đoán của bệnh nhân " + dto.getName() +" được lưu thành công.");
		} catch (Exception e) {
			result.setResult("Lưu hồ sơ chẩn đoán thất bại");
		}
		return result;
	}

	@Override
	public ResultBloodTest Update(BloodTestDto dto) {
		ResultBloodTest result = new ResultBloodTest();
		try {
			BloodTestEntity entity = new BloodTestEntity();
			entity.setBloodtestId(dto.getBloodtestId());
			entity.setUserId(dto.getUserId());
			entity.setDoctorId(dto.getDoctorId());
			entity.setDayCare(dto.getDayCare());
			entity.setGender(dto.getGender());
			entity.setIsResult(dto.getIsResult());
			entity.setIsBloodTest(dto.getIsBloodTest());
			entity.setDiagnose(dto.getDiagnose());
			entity.setName(dto.getName());
			entity.setAddress(dto.getAddress());
			entity.setResult(dto.getResult());
			entity.setVersion(dto.getVersion());
			entity.setValueUre(dto.getValueUre());
			entity.setValueFe(dto.getValueFe());
			entity.setValueGlucose(dto.getValueGlucose());
			entity.setValueMagie(dto.getValueMagie());
			entity.setValueCreatinin(dto.getValueCreatinin());
			entity.setValueAstgot(dto.getValueAstgot());
			entity.setValueAcidUric(dto.getValueAcidUric());
			entity.setValueAltgpt(dto.getValueAltgpt());
			entity.setValueBilirubintp(dto.getValueBilirubintp());
			entity.setValueAmylase(dto.getValueAmylase());
			entity.setValueBilirubintt(dto.getValueBilirubintt());
			entity.setValueCk(dto.getValueCk());
			entity.setValueBilirubingt(dto.getValueBilirubingt());
			entity.setValueCkmb(dto.getValueCkmb());
			entity.setValueProteintp(dto.getValueProteintp());
			entity.setValueLdh(dto.getValueLdh());
			entity.setValueAlbunmin(dto.getValueAlbunmin());
			entity.setValueGgt(dto.getValueGgt());
			entity.setValueGlobulin(dto.getValueGlobulin());
			entity.setValueCholinesterase(dto.getValueCholinesterase());
			entity.setValueRateag(dto.getValueRateag());
			entity.setValuePhosphatase(dto.getValuePhosphatase());
			entity.setValueFibrinogen(dto.getValueFibrinogen());
			entity.setValueCholesterol(dto.getValueCholesterol());
			entity.setValuePhArtery(dto.getValuePhArtery());
			entity.setValueTriglycerid(dto.getValueTriglycerid());
			entity.setValuePco2(dto.getValuePco2());
			entity.setValueHdlcho(dto.getValueHdlcho());
			entity.setValuePo2Artery(dto.getValuePo2Artery());
			entity.setValueLdlcho(dto.getValueLdlcho());
			entity.setValueStandardHco3(dto.getValueStandardHco3());
			entity.setValueNaplus(dto.getValueNaplus());
			entity.setValueAlkalineBalance(dto.getValueAlkalineBalance());
			entity.setValueKplus(dto.getValueKplus());
			entity.setValueClSubtract(dto.getValueClSubtract());
			entity.setValueCalci(dto.getValueCalci());
			entity.setValueCalciIon(dto.getValueCalciIon());
			entity.setValuePhosho(dto.getValuePhosho());
			this.entityManager.merge(entity);
			result.setResult("update thành công");
		} catch (Exception e) {
			result.setResult("update thất bại");
		}
		return result;
	}

	@Override
	public BloodTestDto getBloodTestId(String bloodtestId) {
		TypedQuery<BloodTestEntity> query = this.entityManager.createQuery(FIND_BLOOD_TESTID,BloodTestEntity.class).setParameter("bloodtestId", bloodtestId);
		if(query.getResultList().size() > 0){
			BloodTestEntity dto = query.getResultList().get(0);
			BloodTestDto entity = new BloodTestDto();
			entity.setBloodtestId(dto.getBloodtestId());
			entity.setUserId(dto.getUserId());
			entity.setDoctorId(dto.getDoctorId());
			entity.setDayCare(dto.getDayCare());
			entity.setGender(dto.getGender());
			entity.setIsResult(false);
			entity.setIsBloodTest(false);
			entity.setDiagnose(dto.getDiagnose());
			entity.setName(dto.getName());
			entity.setAddress(dto.getAddress());
			entity.setResult(dto.getResult());
			entity.setVersion(dto.getVersion());
			entity.setValueUre(dto.getValueUre());
			entity.setValueFe(dto.getValueFe());
			entity.setValueGlucose(dto.getValueGlucose());
			entity.setValueMagie(dto.getValueMagie());
			entity.setValueCreatinin(dto.getValueCreatinin());
			entity.setValueAstgot(dto.getValueAstgot());
			entity.setValueAcidUric(dto.getValueAcidUric());
			entity.setValueAltgpt(dto.getValueAltgpt());
			entity.setValueBilirubintp(dto.getValueBilirubintp());
			entity.setValueAmylase(dto.getValueAmylase());
			entity.setValueBilirubintt(dto.getValueBilirubintt());
			entity.setValueCk(dto.getValueCk());
			entity.setValueBilirubingt(dto.getValueBilirubingt());
			entity.setValueCkmb(dto.getValueCkmb());
			entity.setValueProteintp(dto.getValueProteintp());
			entity.setValueLdh(dto.getValueLdh());
			entity.setValueAlbunmin(dto.getValueAlbunmin());
			entity.setValueGgt(dto.getValueGgt());
			entity.setValueGlobulin(dto.getValueGlobulin());
			entity.setValueCholinesterase(dto.getValueCholinesterase());
			entity.setValueRateag(dto.getValueRateag());
			entity.setValuePhosphatase(dto.getValuePhosphatase());
			entity.setValueFibrinogen(dto.getValueFibrinogen());
			entity.setValueCholesterol(dto.getValueCholesterol());
			entity.setValuePhArtery(dto.getValuePhArtery());
			entity.setValueTriglycerid(dto.getValueTriglycerid());
			entity.setValuePco2(dto.getValuePco2());
			entity.setValueHdlcho(dto.getValueHdlcho());
			entity.setValuePo2Artery(dto.getValuePo2Artery());
			entity.setValueLdlcho(dto.getValueLdlcho());
			entity.setValueStandardHco3(dto.getValueStandardHco3());
			entity.setValueNaplus(dto.getValueNaplus());
			entity.setValueAlkalineBalance(dto.getValueAlkalineBalance());
			entity.setValueKplus(dto.getValueKplus());
			entity.setValueClSubtract(dto.getValueClSubtract());
			entity.setValueCalci(dto.getValueCalci());
			entity.setValueCalciIon(dto.getValueCalciIon());
			entity.setValuePhosho(dto.getValuePhosho());
			return entity;
		}
		return null;
	}

	@Override
	public List<BloodTestDto> getBloodDoctorId(String doctorId) {
		List<BloodTestEntity> entitys = this.entityManager.createQuery(FIND_ALL_FOLLOW_DOCTORID,BloodTestEntity.class).setParameter("doctorId", doctorId).getResultList();
		if(entitys != null){
			List<BloodTestDto> bloodTestDtos = new ArrayList<>();
			for(BloodTestEntity entity : entitys){
				BloodTestDto dto = new BloodTestDto();
				dto.setBloodtestId(entity.getBloodtestId());
				dto.setDayCare(entity.getDayCare());
				dto.setUserId(entity.getUserId());
				dto.setName(entity.getName());
				bloodTestDtos.add(dto);
			}
			return bloodTestDtos;
		}
		return null;
	}

	@Override
	public List<BloodTestDto> getNonvalueBloodTest() {
		List<BloodTestEntity> entitys = this.entityManager.createQuery(FIND_ALL_NON_BLOOD_TEST,BloodTestEntity.class).getResultList();
		if(entitys != null){
			List<BloodTestDto> bloodTestDtos = new ArrayList<>();
			for(BloodTestEntity entity : entitys){
				BloodTestDto dto = new BloodTestDto();
				dto.setBloodtestId(entity.getBloodtestId());
				dto.setDayCare(entity.getDayCare());
				dto.setUserId(entity.getUserId());
				dto.setName(entity.getName());
				bloodTestDtos.add(dto);
			}
			return bloodTestDtos;
		}
		return null;
	}

	@Override
	public List<BloodTestDto> getAllUserId(String userId) {
		
		List<BloodTestEntity> entitys = this.entityManager.createQuery(FINDUSERID,BloodTestEntity.class).setParameter("userId", userId).getResultList();
		if(entitys != null){
			List<BloodTestDto> bloodTestDtos = new ArrayList<>();
			for(BloodTestEntity entity : entitys){
				BloodTestDto dto = new BloodTestDto();
				dto.setBloodtestId(entity.getBloodtestId());
				dto.setDayCare(entity.getDayCare());
				dto.setUserId(entity.getUserId());
				dto.setName(entity.getName());
				bloodTestDtos.add(dto);
			}
			return bloodTestDtos;
		}
		return null;
	}

}
