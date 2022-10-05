package com.campin.manager;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

//import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
//import java.util.Base64;
import org.apache.tomcat.util.codec.binary.Base64;
//import org.apache.commons.codec.binary.Base64;
public class AES {
	String iv;
	Key keySpec;
	final static String key="tlssksmszheldtlrks";
	
	public AES() {
		try {
			
			iv = key.substring(0, 16);
			byte[] keyBytes = new byte[16];
			byte[] b = key.getBytes("utf-8");
			int len = b.length;
			if(len > keyBytes.length) len = keyBytes.length;
			
			System.arraycopy(b, 0, keyBytes, 0, len);
			SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
			this.keySpec = keySpec;
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public String enc(String str) {
		
		String encStr = "";
		
		try {
			Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
			c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
			byte[] encrypted = c.doFinal(str.getBytes("utf-8"));
			encStr = new String(Base64.encodeBase64(encrypted));
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return encStr;
	}
	
	public String dec(String str) {
		
		String decStr = "";
		Cipher c = null;
		byte[] byteStr = null;
		
		try {
			c = Cipher.getInstance("AES/CBC/PKCS5Padding");
			c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
			byteStr = Base64.decodeBase64(str.getBytes()); 
			decStr = new String(c.doFinal(byteStr), "utf-8");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		
		return decStr;
	}
	
	
	public static void main(String[] args) {
		
		AES aes = new AES();	
		//String msg = "asfsf";
		//String rMsg = aes.enc(msg); 
		//System.out.println(msg);    // tst완
		//System.out.println(rMsg);   // tst완
		//String rStr = aes.dec(rMsg);
		//System.out.println(rStr);   // tst완
	}

}
