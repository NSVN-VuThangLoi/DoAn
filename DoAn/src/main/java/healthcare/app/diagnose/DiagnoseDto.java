package healthcare.app.diagnose;

import lombok.Data;

@Data
public class DiagnoseDto {
	private String userId;
	
	private String name;
	
	private String diagnose;
	
	private Boolean supersonic;
	private Boolean takeXQuang;
	private Boolean ctScanner;
}
