package healthcare.infra.entity.bloodtest;

import java.io.Serializable;
import java.math.BigDecimal;
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
	    private BigDecimal valueUre;
	    
	    @Column(name = "value_fe")
	    private BigDecimal valueFe;
	    
	    @Column(name = "value_glucose")
	    private BigDecimal valueGlucose;
	    
	    @Column(name = "value_magie")
	    private BigDecimal valueMagie;
	    
	    @Column(name = "value_creatinin")
	    private BigDecimal valueCreatinin;
	    
	    @Column(name = "value_astgot")
	    private BigDecimal valueAstgot;
	    
	    @Column(name = "value_acid_uric")
	    private BigDecimal valueAcidUric;
	    
	    @Column(name = "value_altgpt")
	    private BigDecimal valueAltgpt;
	    
	    @Column(name = "value_bilirubintp")
	    private BigDecimal valueBilirubintp;
	    
	    @Column(name = "value_amylase")
	    private BigDecimal valueAmylase;
	    
	    @Column(name = "value_bilirubintt")
	    private BigDecimal valueBilirubintt;
	    
	    @Column(name = "value_ck")
	    private BigDecimal valueCk;
	    
	    @Column(name = "value_bilirubingt")
	    private BigDecimal valueBilirubingt;
	    
	    @Column(name = "value_ckmb")
	    private BigDecimal valueCkmb;
	    
	    @Column(name = "value_proteintp")
	    private BigDecimal valueProteintp;
	    
	    @Column(name = "value_ldh")
	    private BigDecimal valueLdh;
	    
	    @Column(name = "value_albunmin")
	    private BigDecimal valueAlbunmin;
	    
	    @Column(name = "value_ggt")
	    private BigDecimal valueGgt;
	    
	    @Column(name = "value_globulin")
	    private BigDecimal valueGlobulin;
	    
	    @Column(name = "value_cholinesterase")
	    private BigDecimal valueCholinesterase;
	    
	    @Column(name = "value_rateag")
	    private BigDecimal valueRateag;
	    
	    @Column(name = "value_phosphatase")
	    private BigDecimal valuePhosphatase;
	    
	    @Column(name = "value_fibrinogen")
	    private BigDecimal valueFibrinogen;
	    
	    @Column(name = "value_cholesterol")
	    private BigDecimal valueCholesterol;
	    
	    @Column(name = "value_ph_artery")
	    private BigDecimal valuePhArtery;
	    
	    @Column(name = "value_triglycerid")
	    private BigDecimal valueTriglycerid;
	    
	    @Column(name = "value_pco2")
	    private BigDecimal valuePco2;
	    
	    @Column(name = "value_hdlcho")
	    private BigDecimal valueHdlcho;
	    
	    @Column(name = "value_po2_artery")
	    private BigDecimal valuePo2Artery;
	    
	    @Column(name = "value_ldlcho")
	    private BigDecimal valueLdlcho;
	    
	    @Column(name = "value_standard_hco3")
	    private BigDecimal valueStandardHco3;
	    
	    @Column(name = "value_naplus")
	    private BigDecimal valueNaplus;
	    
	    @Column(name = "value_alkaline_balance")
	    private BigDecimal valueAlkalineBalance;
	    
	    @Column(name = "value_kplus")
	    private BigDecimal valueKplus;
	    
	    @Column(name = "value_cl_subtract")
	    private BigDecimal valueClSubtract;
	    
	    @Column(name = "value_calci")
	    private BigDecimal valueCalci;
	    
	    @Column(name = "value_calci_ion")
	    private BigDecimal valueCalciIon;
	    
	    @Column(name = "value_phosho")
	    private BigDecimal valuePhosho;
	    
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
