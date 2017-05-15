package healthcare.app.bloodtest;

import lombok.Data;

@Data
public class UpdateBloodtestCommand {

	private String bloodTestId;
	private String result;
}
