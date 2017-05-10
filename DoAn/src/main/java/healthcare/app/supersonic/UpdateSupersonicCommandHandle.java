package healthcare.app.supersonic;

import javax.ejb.Stateless;
import javax.inject.Inject;

import healthcare.domain.supersonic.SupersonicDto;
import healthcare.domain.supersonic.SupersonicRepository;
import healthcare.domain.supersonic.SupersonicResult;
@Stateless
public class UpdateSupersonicCommandHandle {
	@Inject
	private SupersonicRepository supersonicRep;
	public SupersonicResult handle(SupersonicDto dto){
		SupersonicResult result = new SupersonicResult();
		try {
			supersonicRep.updateSupersonic(dto);
			result.setResult("update thành công");
		} catch (Exception e) {
			// TODO: handle exception
			result.setResult("update thất bại");
		}
		return result;
	}

}
