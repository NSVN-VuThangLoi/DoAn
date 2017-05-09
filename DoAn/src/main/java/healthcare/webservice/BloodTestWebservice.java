package healthcare.webservice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import healthcare.app.bloodtest.FinderBloodTest;
import healthcare.app.readfile.ReadFile;
import healthcare.domain.bloodtest.BloodTestDto;

@Path("/bloodTest")
@Stateless
public class BloodTestWebservice {
	@Inject
		private FinderBloodTest finder;
	@Inject
		private ReadFile readFile;
	
	private static final String SAVE_FOLDER = "D:\\BloodTest\\";
	private final String URL_IMAGE = "D:\\Xquang\\image\\";
	
	@POST
	@Path("/upload")
	@Produces(MediaType.MULTIPART_FORM_DATA)
	@Consumes("multipart/form-data")
	public String uploadFile(MultipartFormDataInput input) throws Exception{
		String fileName = "";
		String xquangId = "";
		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("file");
		for (InputPart inputPart : inputParts) {

			try {

				MultivaluedMap<String, String> header = inputPart.getHeaders();
				fileName = getFileName(header);

				// convert the uploaded file to inputstream
				InputStream inputStream = inputPart.getBody(InputStream.class, null);

				byte[] bytes = IOUtils.toByteArray(inputStream);

				
				fileName = SAVE_FOLDER + "loi" + ".csv";
				writeFile(bytes, fileName);
				readFile.getFileCsv(fileName);
				System.out.println(fileName);

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return "thanh coong";
	}
	@POST
	@Path("/getIsBloodTest")
	public List<BloodTestDto> getAllNonBloodTest(){
		return finder.getAllNonBloodTest();
	}
	@POST
	@Path("/getBloodtest")
	public BloodTestDto getBlooTest(String bloodTestId){
		return finder.getBloodTest(bloodTestId);
	}
	
	
	private String getFileName(MultivaluedMap<String, String> header) {

		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {
				String[] name = filename.split("=");
				String finalFileName = name[1].trim().replaceAll("\"", "");
				return finalFileName;
			}
		}
		return "unknown";
	}

	// save to somewhere
	private void writeFile(byte[] content, String filename) throws IOException {

		File file = new File(filename);

		if (!file.exists()) {
			file.createNewFile();
		}

		FileOutputStream fop = new FileOutputStream(file);

		fop.write(content);
		fop.flush();
		fop.close();

	}

}
