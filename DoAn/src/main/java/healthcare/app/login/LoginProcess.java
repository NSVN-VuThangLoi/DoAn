package healthcare.app.login;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

import healthcare.app.common.HashGenerator;
import healthcare.app.common.UserLogin;
import healthcare.domain.login.LoginDto;
import healthcare.domain.login.LoginRepository;

@Stateless
public class LoginProcess {
	@Inject
	private LoginRepository infoLogin;
	
	@Inject
	private HashGenerator generator;
	
	@Inject 
	private UserLogin userInfoLogin;
	public LoginResult handle(LoginQuery query) {

	//	LoginDto dto = infoLogin.findData(query.getUserId());
		
		LoginResult result = new LoginResult();
		result.setLinkTopPage("a.html");
		
//		if(dto != null){
//			result.setNameNotice("Tên đăng kí đã có người sử dụng");
//			result.setStatus(false);
//		}
//		else{
//			LoginDto loginDto = new LoginDto();
//			loginDto.setPassword(generator.generatorSha256(query.getPassword()+ query.getUserId()));
//			loginDto.setName(query.getUserId());
//			loginDto.setUserId(generator.generatorMd2(""+ new Date().getTime()+query.getUserId()));
//			loginDto.setVersion(1L);
//			
//			infoLogin.insertData(loginDto);
//			result.setNameNotice("Đăng kí thành công ");
//			result.setUserId(loginDto.getUserId());
//			result.setUserName(loginDto.getName());
//			result.setLinkTopPage("/Note/data/index.xhtml");
//			result.setStatus(true);
//			
//			userInfoLogin.setUserId(loginDto.getUserId());
//		}
		return result;
	}
	
	public LoginResult checkLogin(LoginQuery query) {

		LoginDto dto = infoLogin.findLogin(query.getUserId(),generator.generatorSha256(query.getPassword()+ query.getUserId()));
		
		LoginResult result = new LoginResult();
		
		if(dto == null){
			result.setNameNotice("Không đúng, nhập lại");
			result.setStatus(false);
		}
		else{
			result.setNameNotice("Đăng nhập thành công ");
			result.setUserId(dto.getUserId());
			result.setUserName(dto.getName());
			result.setLinkTopPage("/Note/data/index.xhtml");
			result.setStatus(true);
			userInfoLogin.setUserId(dto.getUserId());
		}
		return result;
	}

}
