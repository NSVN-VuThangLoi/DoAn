package healthcare.domain.doctor;

import java.util.List;

public interface DoctorRepository {
	
void insertDoctor(DoctorDto dto);
List<DoctorDto> getAllDoctor();
DoctorDto getDoctor(String userId);
void remove(String userId);

}
