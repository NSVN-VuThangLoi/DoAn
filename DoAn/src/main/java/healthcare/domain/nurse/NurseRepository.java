package healthcare.domain.nurse;

import java.util.List;

public interface NurseRepository {
	
void insertNurse(NurseDto dto);
List<NurseDto> getAllNurse();
NurseDto getNurse(String nurseId);
void remove(String nurseId);
void updateNurse(NurseDto dto);

}
