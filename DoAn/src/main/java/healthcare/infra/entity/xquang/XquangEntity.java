package healthcare.infra.entity.xquang;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Xquang")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class XquangEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Version
	private long version;

	@Id
	@Column(name = "user_id")
	private String userId;

	@Column(name = "doctor_id")
	private String doctorId;
	
	@Column(name = "age")
	private String age;
	
	@Column(name = "day_care")
	private Date dayCare;
	
	@Column(name = "diagnose")
	private String diagnose;
	
	@Column(name = "result")
	private String result;
	
	@Column(name = "address_Patient")
	private String addressPatient;
	
	@Column(name ="url_image")
	private String urlImage;
	
	@Column(name ="isIamge")
	private Boolean isIamge;
}
