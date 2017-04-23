package healthcare.app.common;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ejb.Stateless;

@Stateless
public class HashGenerator {
	public String generatorSha256(String input) {
		MessageDigest objSHA256 = null;
		try {
			objSHA256 = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] bytSHA256 = objSHA256.digest(input.getBytes());
		BigInteger intNumSHA256 = new BigInteger(1, bytSHA256);
		String hcSHA256 = intNumSHA256.toString(16);
		while (hcSHA256.length() < 64) {
			hcSHA256 = "0" + hcSHA256;
		}
		return hcSHA256;
	}

	public String generatorMd2(String input) {
		MessageDigest objMD2 = null;
		try {
			objMD2 = MessageDigest.getInstance("MD2");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] bytMD2 = objMD2.digest(input.getBytes());
		BigInteger intNumMD2 = new BigInteger(1, bytMD2);
		String hcMD2 = intNumMD2.toString(16);
		while (hcMD2.length() < 32) {
			hcMD2 = "0" + hcMD2;
		}
		return hcMD2;
	}

}
