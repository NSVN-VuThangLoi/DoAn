package healthcare.app.readfile;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;
import org.dcm4che2.io.DicomInputStream;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import healthcare.domain.bloodtest.BloodTestDto;

@Stateless
public class ReadFile {
	public String getImage(String file, String urlImage) throws IOException {
		  String result = "";
////		    String manufacturer, a = null;
		  
		  			File fileTest = new File(file);
		            DicomObject dcmObj4;
		            DicomInputStream din4 = null;
		            din4 = new DicomInputStream(fileTest);
		            dcmObj4 = din4.readDicomObject();

//		            manufacturer = dcmObj4.getString(Tag.Manufacturer);
//		            System.out.println(manufacturer);
//		            a = dcmObj4.getString(Tag.PixelData);
//		            System.out.println(a);
		            System.out.println( dcmObj4.getString(Tag.SamplesPerPixel));
		            System.out.println(dcmObj4.getString(Tag.PhotometricInterpretation));
		            System.out.println(dcmObj4.getString(Tag.PlanarConfiguration));
		            System.out.println(dcmObj4.getString(Tag.NumberOfFrames));
		            System.out.println(dcmObj4.getString(Tag. Rows));
		            System.out.println(dcmObj4.getString(Tag.Columns));
		            System.out.println(dcmObj4.getString(Tag.BitsAllocated));
		            System.out.println(dcmObj4.getString(Tag.BitsStored));
		            System.out.println(dcmObj4.getString(Tag.HighBit));
		            System.out.println(dcmObj4.getString(Tag.PixelRepresentation));
		            System.out.println(dcmObj4.getString(Tag.LossyImageCompression));
		            System.out.println(dcmObj4.getString(Tag.LossyImageCompressionRatio));
		            System.out.println(dcmObj4.getString(Tag.LossyImageCompressionMethod));
		            
		          Iterator<ImageReader> iter = ImageIO.getImageReadersByFormatName("DICOM");//specifation dicom	

		  		  ImageReader readers = iter.next();//give other tag 

		  		  ImageReadParam param =  readers.getDefaultReadParam();//return DicomImageReadParam
//		  		  	Adjust the values of Rows and Columns in it and add a Pixel Data attribute with the byte array from the DataBuffer of the scaled Raster 

		  		  ImageInputStream iis = ImageIO.createImageInputStream(fileTest);//give image input 

		  		  readers.setInput(iis, true);//sets the input source to use the given ImageInputSteam or other Object 

		  		BufferedImage  myJpegImage= readers.read(0, param); //read dicom with indice(number of frame) and param (height, weight, alpha)
		  		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		  		ImageIO.write(myJpegImage, "jpg", baos);
		  		baos.flush();
//		  		String patientId=dcmObj4.getString(Tag.PatientID);
		  		File myJpegFile = new File(urlImage); //création du fichier
		  		OutputStream output = new BufferedOutputStream(new FileOutputStream(myJpegFile)); //mise des fichiers en sortie
		  		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(output);//convertion en sortie 
		  		encoder.encode(myJpegImage);//Create encodage
		  		din4.close();
		    return result;
		    
		} 
	public BloodTestDto getFileCsv(String file){
		BloodTestDto dto = new BloodTestDto();

		File myFile = new File(file);
        FileInputStream fis = null;
		try {
			fis = new FileInputStream(myFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Finds the workbook instance for XLSX file
        XSSFWorkbook myWorkBook = null;
		try {
			myWorkBook = new XSSFWorkbook (fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        // Return first sheet from the XLSX workbook
        XSSFSheet mySheet = myWorkBook.getSheetAt(0);
       
        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = mySheet.iterator();
        // Traversing over each row of XLSX file
        List<String> item = new ArrayList<>();
        List<Double> value = new ArrayList<>();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            
            // For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();
            
            while (cellIterator.hasNext()) {

                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    System.out.print(cell.getStringCellValue() + "\t");
                    item.add(cell.getStringCellValue());
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    System.out.print(cell.getNumericCellValue() + "\t");
                    value.add(cell.getNumericCellValue());
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    System.out.print(cell.getBooleanCellValue() + "\t");
                    break;
                default :
             
                }
            }
            System.out.println("");
        }
        dto = insertValue(item,value);
        return dto;
    }
	private BloodTestDto insertValue(List<String> item,List<Double> value){
		BloodTestDto dto = new BloodTestDto();
		for(int i = 0; i < item.size();i++){
			switch (item.get(i)){
			case "Ure":
				if(value.get(i) != null){
					dto.setValueUre(value.get(i));
				}
				break;
			case "Glucose":
				dto.setValueGlucose(value.get(i));
				break;
			case "Creatinin":
				dto.setValueCreatinin(value.get(i));
				break;
			case "Acid Uric":
				dto.setValueAcidUric(value.get(i));
				break;
			case "BilirubinT.T":
				dto.setValueBilirubintt(value.get(i));
				break;
			case "BilirubinT.P":
				dto.setValueBilirubintp(value.get(i));
				break;
			case "BilirubinG.T":
				dto.setValueBilirubingt(value.get(i));
				break;
			case "ProteinT.P":
				dto.setValueProteintp(value.get(i));
				break;
			case "Albunmin":
				dto.setValueAlbunmin(value.get(i));
				break;
			case "A/G":
				dto.setValueRateag(value.get(i));
				break;
				
			case "Fibrinogen":
				dto.setValueFibrinogen(value.get(i));
				break;
			case "Cholesterol":
				dto.setValueCholesterol(value.get(i));
				break;
			case "Triglycerid":
				dto.setValueTriglycerid(value.get(i));
				break;
			case "HDL-cho.":
				dto.setValueHdlcho(value.get(i));
				break;
			case "LDL-cho.":
				dto.setValueLdlcho(value.get(i));
				break;
			case "Na+":
				dto.setValueNaplus(value.get(i));
				break;
			case "K+":
				dto.setValueKplus(value.get(i));
				break;
			case "Cl-":
				dto.setValueClSubtract(value.get(i));
				break;
			case "Calci":
				dto.setValueCalci(value.get(i));
				break;
			case "Calci ion":
				dto.setValueCalciIon(value.get(i));
				break;
			case "Phospho":
				dto.setValuePhosho(value.get(i));
				break;
			case "Fe":
				dto.setValueFe(value.get(i));
				break;
			case "Magie":
				dto.setValueMagie(value.get(i));
				break;
			case "AST(GOT)":
				dto.setValueAstgot(value.get(i));
				break;
			case "ALT(GPT)":
				dto.setValueAltgpt(value.get(i));
				break;
			case "Amylase":
				dto.setValueAmylase(value.get(i));
				break;
			case "CK":
				dto.setValueCk(value.get(i));
				break;
			case "CK-MB":
				dto.setValueCkmb(value.get(i));
				break;
			case "LDH":
				dto.setValueLdh(value.get(i));
				break;
			case "GGT":
				dto.setValueGgt(value.get(i));
				break;
			case "Cholinesterase":
				dto.setValueCholinesterase(value.get(i));
				break;
			case "Phosphatase Kiềm":
				dto.setValuePhosphatase(value.get(i));
				break;
			case "pH động mạch":
				dto.setValuePhArtery(value.get(i));
				break;
			case "pCO2":
				dto.setValuePco2(value.get(i));
				break;
			case "pO2 động mạch":
				dto.setValuePo2Artery(value.get(i));
				break;
			case "HCO3 chuẩn":
				dto.setValueStandardHco3(value.get(i));
				break;
			case "Kiềm dư":
				dto.setValueAlkalineBalance(value.get(i));
				break;
			case "Globulin":
				dto.setValueGlobulin(value.get(i));
				break;
				
				default:
			}
		}
		return dto;
	}
	
}
