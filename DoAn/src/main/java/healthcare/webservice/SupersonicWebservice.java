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
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import healthcare.app.common.UserLogin;
import healthcare.app.readfile.ReadFile;
import healthcare.app.supersonic.FinderSupersonic;
import healthcare.app.supersonic.UpdateForDoctorSupersonic;
import healthcare.app.supersonic.UpdateSupersonicCommandHandle;
import healthcare.domain.supersonic.SupersonicDto;

@Path("/supersonic")
@Stateless
public class SupersonicWebservice {
	@Inject
	private ReadFile readfile;
	@Inject 
	private FinderSupersonic find;
	@Inject
	private UpdateSupersonicCommandHandle handle;
	@Inject
	private UserLogin userLogin;
	
	private static final String SAVE_FOLDER = "D:\\fileproject\\Supersonic\\dicom\\";
	private final String URL_IMAGE = "D:\\fileproject\\Supersonic\\image\\";

	@POST
	@Path("/upload")
	@Produces(MediaType.MULTIPART_FORM_DATA)
	@Consumes("multipart/form-data")
	public Response uploadFile(MultipartFormDataInput input) throws IOException {
		String fileName = "";
		String supersonicId = "";
		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("file");
		List<InputPart> inputSupersonicId = uploadForm.get("supersonicId");
		for (InputPart inputPart : inputSupersonicId) {
				InputStream inputStream = inputPart.getBody(InputStream.class,null);

				byte [] bytes = IOUtils.toByteArray(inputStream);
				supersonicId = new String(bytes);
				
		}
		for (InputPart inputPart : inputParts) {
			try {
				MultivaluedMap<String, String> header = inputPart.getHeaders();
				fileName = getFileName(header);

				// convert the uploaded file to inputStream
				InputStream inputStream = inputPart.getBody(InputStream.class, null);

				byte[] bytes = IOUtils.toByteArray(inputStream);

				
				fileName = SAVE_FOLDER + supersonicId + ".dcm";
				String fileUrl = URL_IMAGE + supersonicId +".PNG";
				writeFile(bytes, fileName);
				String result = readfile.getImage(fileName,fileUrl);
				SupersonicDto dto = find.getSupersonicId(supersonicId);
				dto.setUrlImage(fileUrl);
				dto.setIsImage(true);
				handle.handle(dto);
				System.out.println(result);

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return Response.status(200).entity("uploadFile is called, Uploaded file name : " + fileName).build();
	}
	
	@POST
	@Path("/getNonImage")
	public List<SupersonicDto> getAllNonXquang(){
		return find.getAllNonImage();
	}
	
	@POST
	@Path("/getSupersonicId")
	public SupersonicDto getSupersonicId(String supersonicId){
		return find.getSupersonicId(supersonicId);
	}
	@POST
	@Path("/getConformDoctorId")
	public List<SupersonicDto> getConformDoctorId(){
		String doctorId = userLogin.getDoctorId();
		return find.getAllDoctorId(doctorId);
	}
	@POST
	@Path("/updateSupersonic")
	public String update(UpdateForDoctorSupersonic dto){
		String result ="";
		try {
			SupersonicDto supersonicDto = find.getSupersonicId(dto.getSupersonicId());
			supersonicDto.setResult(dto.getResult());
			supersonicDto.setIsResult(true);
			handle.handle(supersonicDto);
			result ="update thành công";
		} catch (Exception e) {
			// TODO: handle exception
			result ="update thất bại";
		}
		return result;
	}
	
	@POST
	@Path("/getConformUserId")
	public List<SupersonicDto> getConformUserId(){
		String userId = userLogin.getUserId();
		return find.getUserId(userId);
	}
	
	// get uploaded filename, is there a easy way in RESTEasy?
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
