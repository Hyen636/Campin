package com.campin.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.campin.partner.ItemAtt;

@Service
public class FileUploadController {
	public static String uploadPath = "C:\\2022_02\\Campin\\src\\main\\resources\\static\\image\\camping\\";

	public List<ItemAtt> uploadCamp(List<MultipartFile> multi, int itemCode, int hisSno, String type){
		List<ItemAtt> attList = new ArrayList<ItemAtt>();
		UUID uuid = UUID.randomUUID();

		try {
			for(MultipartFile mf : multi) {	// 들어오는 파일들의 형태
				if(mf.getOriginalFilename().equals("")) continue;
				ItemAtt att = new ItemAtt();
				String oriFile = mf.getOriginalFilename();
				String sysFile = String.format("%s-%s", uuid.getLeastSignificantBits(), oriFile);
				File file = null;
				File Folder = null;
				String campPath = uploadPath + itemCode +"\\";
				String mapPath = campPath + "map\\";
				String sitePath = campPath + type + "\\";
				
				// type은 map, 캠핑장 사진, site코드
				if(type == "map") {					// 배치도 저장
					Folder = new File(campPath);
					if(!Folder.exists()) {
						try {
							Folder.mkdir();
						} catch(Exception e) {
							e.printStackTrace();
						}
					}
					
					Folder = new File(mapPath);
					if(!Folder.exists()) {
						try {
							Folder.mkdir();
						} catch(Exception e) {
							e.printStackTrace();
						}
					}
					file = new File(mapPath + sysFile);
					att.setItemType("map");
					att.setHisItemSno(hisSno);
				} else if(type == "camp") {			// 캠핑장 저장
					Folder = new File(campPath);
					if(!Folder.exists()) {
						try {
							Folder.mkdir();
						} catch(Exception e) {
							e.printStackTrace();
						}
					}
					file = new File(campPath + sysFile);
					att.setItemType("WH");	
					att.setHisItemSno(hisSno);
				} else { 	// 사이트 저장
					Folder = new File(sitePath);
					if(!Folder.exists()) {
						try {
							Folder.mkdir();
						} catch(Exception e) {
							e.printStackTrace();
						}
					}
					file = new File(sitePath + sysFile);
					att.setItemType(type);	
					att.setHisDetailSno(hisSno);
				}
				
				mf.transferTo(file);	// sysFile을 경로에 전송
				
				att.setOriFile(oriFile);
				att.setSysFile(sysFile);
				att.setItemCode(itemCode);

				attList.add(att);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	
		return attList;
	}
}
