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
import healthcare.app.xquang.FinderXquang;
import healthcare.app.xquang.UpdateForDoctor;
import healthcare.app.xquang.UpdateXquangCommandHandle;
import healthcare.domain.xquang.XquangDto;

@Path("/xquang")
@Stateless
public class XquangWebservice {
	@Inject
	private ReadFile readfile;
	@Inject 
	private FinderXquang find;
	@Inject
	private UpdateXquangCommandHandle handle;
	@Inject
	private UserLogin userLogin;
	
	private static final String SAVE_FOLDER = "D:\\fileproject\\Xquang\\dicom\\";
	private final String URL_IMAGE = "D:\\fileproject\\Xquang\\image\\";

	@POST
	@Path("/upload")
	@Produces(MediaType.MULTIPART_FORM_DATA)
	@Consumes("multipart/form-data")
	public Response uploadFile(MultipartFormDataInput input) throws IOException {
		String fileName = "";
		String xquangId = "";
		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("file");
		List<InputPart> inputXquangId = uploadForm.get("xquangId");
		for (InputPart inputPart : inputXquangId) {
				InputStream inputStream = inputPart.getBody(InputStream.class,null);
				byte [] bytes = IOUtils.toByteArray(inputStream);
				xquangId = new String(bytes);
		}
		for (InputPart inputPart : inputParts) {
			try {
				MultivaluedMap<String, String> header = inputPart.getHeaders();
				fileName = getFileName(header);
				// convert the uploaded file to inputstream
				InputStream inputStream = inputPart.getBody(InputStream.class, null);

				byte[] bytes = IOUtils.toByteArray(inputStream);

				fileName = SAVE_FOLDER + xquangId + ".dcm";
				String fileUrl = URL_IMAGE + xquangId +".PNG";
				writeFile(bytes, fileName);
				String result = readfile.getImage(fileName,fileUrl);
				XquangDto dto = find.getXquangId(xquangId);
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
	public List<XquangDto> getAllNonXquang(){
		return find.getAllNonImage();
	}
	
	@POST
	@Path("/getXquangId")
	public XquangDto getXquangId(String xquangId){
		return find.getXquangId(xquangId);
	}
	@POST
	@Path("/getConformDoctorId")
	public List<XquangDto> getConformDoctorId(){
		String doctorId = userLogin.getDoctorId();
		return find.getAllDoctorId(doctorId);
	}
	@POST
	@Path("/updateXquang")
	public String update(UpdateForDoctor dto){
		String result ="";
		try {
			XquangDto xquangDto = find.getXquangId(dto.getXquangId());
			xquangDto.setResult(dto.getResult());
			xquangDto.setIsResult(true);
			handle.handle(xquangDto);
			result ="update thành công";
		} catch (Exception e) {
			// TODO: handle exception
			result ="update thất bại";
		}
		return result;
	}
	
	@POST
	@Path("/getConformUserId")
	public List<XquangDto> getConformUserId(){
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
