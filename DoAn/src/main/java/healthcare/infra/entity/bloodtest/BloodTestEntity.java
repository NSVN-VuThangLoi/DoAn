package healthcare.infra.entity.bloodtest;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bloodtest")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BloodTestEntity implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 @Column(name = "version")
	    private long version;
	 
	    @Id
	    @Column(name = "bloodtest_id")
	    private String bloodtestId;
	    
	    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	    @Column(name = "value_ure")
	    private Double valueUre;
	    
	    @Column(name = "value_fe")
	    private Double valueFe;
	    
	    @Column(name = "value_glucose")
	    private Double valueGlucose;
	    
	    @Column(name = "value_magie")
	    private Double valueMagie;
	    
	    @Column(name = "value_creatinin")
	    private Double valueCreatinin;
	    
	    @Column(name = "value_astgot")
	    private Double valueAstgot;
	    
	    @Column(name = "value_acid_uric")
	    private Double valueAcidUric;
	    
	    @Column(name = "value_altgpt")
	    private Double valueAltgpt;
	    
	    @Column(name = "value_bilirubintp")
	    private Double valueBilirubintp;
	    
	    @Column(name = "value_amylase")
	    private Double valueAmylase;
	    
	    @Column(name = "value_bilirubintt")
	    private Double valueBilirubintt;
	    
	    @Column(name = "value_ck")
	    private Double valueCk;
	    
	    @Column(name = "value_bilirubingt")
	    private Double valueBilirubingt;
	    
	    @Column(name = "value_ckmb")
	    private Double valueCkmb;
	    
	    @Column(name = "value_proteintp")
	    private Double valueProteintp;
	    
	    @Column(name = "value_ldh")
	    private Double valueLdh;
	    
	    @Column(name = "value_albunmin")
	    private Double valueAlbunmin;
	    
	    @Column(name = "value_ggt")
	    private Double valueGgt;
	    
	    @Column(name = "value_globulin")
	    private Double valueGlobulin;
	    
	    @Column(name = "value_cholinesterase")
	    private Double valueCholinesterase;
	    
	    @Column(name = "value_rateag")
	    private Double valueRateag;
	    
	    @Column(name = "value_phosphatase")
	    private Double valuePhosphatase;
	    
	    @Column(name = "value_fibrinogen")
	    private Double valueFibrinogen;
	    
	    @Column(name = "value_cholesterol")
	    private Double valueCholesterol;
	    
	    @Column(name = "value_ph_artery")
	    private Double valuePhArtery;
	    
	    @Column(name = "value_triglycerid")
	    private Double valueTriglycerid;
	    
	    @Column(name = "value_pco2")
	    private Double valuePco2;
	    
	    @Column(name = "value_hdlcho")
	    private Double valueHdlcho;
	    
	    @Column(name = "value_po2_artery")
	    private Double valuePo2Artery;
	    
	    @Column(name = "value_ldlcho")
	    private Double valueLdlcho;
	    
	    @Column(name = "value_standard_hco3")
	    private Double valueStandardHco3;
	    
	    @Column(name = "value_naplus")
	    private Double valueNaplus;
	    
	    @Column(name = "value_alkaline_balance")
	    private Double valueAlkalineBalance;
	    
	    @Column(name = "value_kplus")
	    private Double valueKplus;
	    
	    @Column(name = "value_cl_subtract")
	    private Double valueClSubtract;
	    
	    @Column(name = "value_calci")
	    private Double valueCalci;
	    
	    @Column(name = "value_calci_ion")
	    private Double valueCalciIon;
	    
	    @Column(name = "value_phosho")
	    private Double valuePhosho;
	    
	    @Basic(optional = false)
	    @Column(name = "user_id")
	    private String userId;
	    
	    @Basic(optional = false)
	    @Column(name = "doctor_id")
	    private String doctorId;
	    
	    @Column(name = "day_care")
	    private Date dayCare;
	    
	    @Column(name = "name")
	    private String name;
	    
	    @Column(name = "gender")
	    private Boolean gender;
	    
	    @Column(name = "diagnose")
	    private String diagnose;
	    
	    @Column(name = "result")
	    private String result;
	    
	    @Column(name ="isresult")
	    private Boolean isResult;
	    
	    @Column(name ="isblood_test")
	    private Boolean isBloodTest;
	    
	    @Column(name ="address")
	    private String address;
	    
}
