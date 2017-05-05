package healthcare.app.xquang;

import javax.ejb.Stateless;
import javax.inject.Inject;

import healthcare.domain.xquang.XquangDto;
import healthcare.domain.xquang.XquangRepository;
import healthcare.domain.xquang.XquangResult;
@Stateless
public class UpdateXquangCommandHandle {
	@Inject
	private XquangRepository xquangRep;
	public XquangResult handle(XquangDto dto){
		XquangResult result = new XquangResult();
		try {
			xquangRep.updateXquang(dto);
			result.setResult("update thành công");
		} catch (Exception e) {
			// TODO: handle exception
			result.setResult("update thất bại");
		}
		return result;
	}

}
