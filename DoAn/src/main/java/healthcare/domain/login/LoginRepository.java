package healthcare.domain.login;

public interface LoginRepository {
	LoginDto findData(String userId);
	
	LoginDto findLogin (String name, String passWord );
	
	LoginDto findUserId (String userId);

	void insertData(LoginDto dto);

	void updateData(LoginDto dto);

	void deleteData(LoginDto dto);
}
