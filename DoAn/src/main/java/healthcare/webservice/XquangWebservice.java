package healthcare.webservice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import healthcare.app.readfile.ReadFile;

@Path("/xquang")
@Stateless
public class XquangWebservice {
	@Inject
	private ReadFile readfile;
	private static final String SAVE_FOLDER = "D:\\Xquang\\";
	private final String UPLOADED_FILE_PATH = "d:\\";

	@POST
	@Path("/upload")
	@Produces(MediaType.MULTIPART_FORM_DATA)
	@Consumes("multipart/form-data")
	public Response uploadFile(MultipartFormDataInput input) throws IOException {
		String fileName = "";
		String userId = "";
		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("file");
		List<InputPart> inputUserId = uploadForm.get("userId");
		for (InputPart inputPart : inputUserId) {
				InputStream inputStream = inputPart.getBody(InputStream.class,null);

				byte [] bytes = IOUtils.toByteArray(inputStream);
				userId = new String(bytes);
				
		}

		for (InputPart inputPart : inputParts) {

			try {

				MultivaluedMap<String, String> header = inputPart.getHeaders();
				fileName = getFileName(header);

				// convert the uploaded file to inputstream
				InputStream inputStream = inputPart.getBody(InputStream.class, null);

				byte[] bytes = IOUtils.toByteArray(inputStream);
				SimpleDateFormat formatDate = new SimpleDateFormat("yyyyymmddhhmmss");
				Date date = new Date();
				String dayCare = formatDate.format(date);
				// constructs upload file path
				fileName = SAVE_FOLDER + userId + dayCare + ".dcm";

				writeFile(bytes, fileName);
				readfile.getImage(fileName);
				System.out.println("Done");

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return Response.status(200).entity("uploadFile is called, Uploaded file name : " + fileName).build();
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
