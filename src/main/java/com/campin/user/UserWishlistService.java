package com.campin.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.campin.mybatis.UserWishlistMapper;

@Service
@Transactional
public class UserWishlistService implements UserWishlistInterface {
	
	@Autowired
	UserWishlistMapper mapper;
	
	@Autowired
	DataSourceTransactionManager transaction;
	TransactionStatus status;
	
	public UserWishlistService() {}
	
	// 찜리스트에 찜 정보 불러오기
	@Override
	public List<UserWishlistVo> selectUser(int userCode) {
		List<UserWishlistVo> imgList = new ArrayList<UserWishlistVo>();
		List<UserWishlistVo> list = null;
		
		try {
			list = mapper.selectUser(userCode);
			
			if(list.size()>0) {
				imgList.add(list.get(0));
				int index = 0;
				
				for(int i=0; i<list.size(); i++) {
					////System.out.println("list sysfile : "+ i +" " +list.get(i).getSysFile());
					if(imgList.get(index).getItemCode() != list.get(i).getItemCode()) {
						imgList.add(list.get(i));
						index++;
					}
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return imgList;
	}
	
	// 찜리스트에서 찜 삭제
	@Override
	public void deleteWish(UserWishlistVo vo) {
		try {
			////System.out.println("dao vo.userCode : "+vo.getUserCode());
			////System.out.println("dao vo.itemCode : "+vo.getItemCode());
			
			int cnt = 0;
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			cnt = mapper.deleteWish(vo);
			if(cnt > 0) {
				transaction.commit(status);		
			}else {
				transaction.rollback(status);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
