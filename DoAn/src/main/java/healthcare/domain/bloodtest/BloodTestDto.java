package healthcare.domain.bloodtest;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;

import lombok.Data;
@Data
public class BloodTestDto {
	
	
	    private long version;
	 
	    private String bloodtestId;
	    
	    
	    private BigDecimal valueUre;
	   
	    private BigDecimal valueFe;
	   
	    private BigDecimal valueGlucose;
	    
	    private BigDecimal valueMagie;
	   
	    private BigDecimal valueCreatinin;
	   
	    private BigDecimal valueAstgot;
	    
	    private BigDecimal valueAcidUric;
	   
	    private BigDecimal valueAltgpt;
	   
	    private BigDecimal valueBilirubintp;
	   
	    private BigDecimal valueAmylase;
	  
	    private BigDecimal valueBilirubintt;
	    
	    private BigDecimal valueCk;
	    
	    private BigDecimal valueBilirubingt;
	    
	    private BigDecimal valueCkmb;
	    
	    private BigDecimal valueProteintp;
	    
	    private BigDecimal valueLdh;
	    
	    private BigDecimal valueAlbunmin;
	    
	    private BigDecimal valueGgt;
	    
	    private BigDecimal valueGlobulin;
	    
	    private BigDecimal valueCholinesterase;
	    
	    private BigDecimal valueRateag;
	    
	    private BigDecimal valuePhosphatase;
	    
	    private BigDecimal valueFibrinogen;
	    
	    private BigDecimal valueCholesterol;
	    
	    private BigDecimal valuePhArtery;
	    
	    private BigDecimal valueTriglycerid;
	    
	    private BigDecimal valuePco2;
	    
	    private BigDecimal valueHdlcho;
	    
	    private BigDecimal valuePo2Artery;
	    
	    private BigDecimal valueLdlcho;
	    
	    private BigDecimal valueStandardHco3;
	    
	    private BigDecimal valueNaplus;
	    
	    private BigDecimal valueAlkalineBalance;
	    
	    private BigDecimal valueKplus;
	    
	    private BigDecimal valueClSubtract;
	    
	    private BigDecimal valueCalci;
	    
	    private BigDecimal valueCalciIon;
	    
	    private BigDecimal valuePhosho;
	    
	    private String userId;
	    
	    private String doctorId;
	    
	    private Date dayCare;
	    
	    private String name;
	    
	    private Boolean gender;
	    
	    private String diagnose;
	    
	    private String result;
	    
	    private Boolean isResult;

	    private Boolean isBloodTest;
	    
	    private String address;

}
