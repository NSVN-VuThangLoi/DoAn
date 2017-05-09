package healthcare.app.readfile;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;
import org.dcm4che2.io.DicomInputStream;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.media.sound.InvalidFormatException;

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
//		String envFilePath = System.getenv("AZURE_FILE_PATH");
//
//        // upload list of files/directory to blob storage
//        File folder = new File(envFilePath);
//        File[] listOfFiles = folder.listFiles();
//
//        for (int i = 0; i < listOfFiles.length; i++) {
//            if (listOfFiles[i].isFile()) {
//                System.out.println("File " + listOfFiles[i].getName());
//
//                Workbook workbook;
//                //int masterSheetColumnIndex = 0;
//                try {
//                        workbook = workbook.create(new FileInputStream(envFilePath + "\\"+ listOfFiles[i].getName()));
//                        // Get the first sheet.
//                        Sheet sheet = workbook.getSheetAt(0);
//                        // Get the first cell.
//                        Row row = sheet.getRow(0);
//                        //Cell cell = row.getCell(0);
//                        for (Cell cell1 : row) {
//                            // Show what is being read.
//                            System.out.println(cell1.toString());   
//                            //masterSheetColumnIndex++;
//                        }
//                        //we will search for column index containing string "Your Column Name" in the row 0 (which is first row of a worksheet
//                        String columnWanted = "Modality Name";
//                        Integer columnNo = null;
//                        //output all not null values to the list
//                        List<Cell> cells = new ArrayList<Cell>();
//
//                        Row firstRow = sheet.getRow(0);
//
//                        for(Cell cell1:firstRow){
//                            if (cell1.getStringCellValue().equals(columnWanted)){
//                                columnNo = cell1.getColumnIndex();
//                            }
//                        }
//
//                        if (columnNo != null){
//                            for (Row row1 : sheet) {
//                               Cell c = row1.getCell(columnNo);
//                               if (c == null || c.getCellType() == Cell.CELL_TYPE_BLANK) {
//                                  // Nothing in the cell in this row, skip it
//                               } else {
//                                  cells.add(c);
//                               }
//                            }
//                            System.out.println("");
//                        }else{
//                            System.out.println("could not find column " + columnWanted + " in first row of " + listOfFiles[i].getName());
//                        }
//
//                } catch (InvalidFormatException | IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        return dto;
    }
	
}
