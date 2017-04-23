package healthcare.domain.patient;

import java.util.List;

public interface PatientRepository {
	
void insertPatient(PatientDto dto);
List<PatientDto> getAllPatient();
PatientDto getPatient(String userId);
void remove(String userId);

}
