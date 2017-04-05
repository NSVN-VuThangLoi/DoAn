package healthcare.app.readfile;

import lombok.Data;
@Data
public class FileDicomDto {
	private String patientId;
	private byte[] image;

}
