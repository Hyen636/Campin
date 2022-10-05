package com.campin.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.campin.partner.JoinAtt;

@Service
public class CjoinFileUploadController {
									
	public static String uploadPath = "C:\\2022_02\\Campin\\src\\main\\resources\\static\\image\\doc\\";
			//List<JoinAtt>
	public JoinAtt uploadCuser(List<MultipartFile> multi, String column, int cUserCode) {
		JoinAtt att = new JoinAtt();	
		UUID uuid = UUID.randomUUID();	
		
		try {
			
			for(MultipartFile mf: multi) {
				
			if(mf.getOriginalFilename().equals("")) continue;
			String oriFile = mf.getOriginalFilename();
			String busisysFile=null;
			String travelsysFile=null;
			File file = null;

			if(column == "busiImg") {
				busisysFile = String.format(cUserCode + "_b" + uuid.getLeastSignificantBits() + oriFile);
				file = new File(uploadPath + busisysFile);
			}else if(column == "travelImg") {
				travelsysFile = String.format(cUserCode + "_t" + uuid.getLeastSignificantBits() + oriFile);
				file = new File(uploadPath + travelsysFile);
			}
			mf.transferTo(file); 
			
			att.setOriFile(oriFile);
			att.setBusiImg(busisysFile);
			att.setTravelImg(travelsysFile);
			att.setcUserCode(cUserCode);
			//attList.add(att);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}

		return att;
	}
	
}
