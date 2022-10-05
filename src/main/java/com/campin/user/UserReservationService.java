package com.campin.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campin.mybatis.UserReservationMapper;
 
@Service
public class UserReservationService implements UserReservationInterface{

	@Autowired
	UserReservationMapper mapper;
	
	@Override
	public List<UserReservationVo> selectR(int userCode) {
		List<UserReservationVo> list = null;
		List<UserReservationVo> listR = new ArrayList<UserReservationVo>();
		
		//System.out.println("selectR userCode = " + userCode);
		try {
			list = mapper.selectR(userCode);
			if(list.size() > 0) {
				listR.add(list.get(0));
				int index = 0;
				for(int i = 0; i < list.size(); i++) {
					if(listR.get(index).getOrderCode() != list.get(i).getOrderCode()) {
						listR.add(list.get(i));
						index++;
					}
				}
				for(UserReservationVo vo : listR) {
					////System.out.println(vo.getSysFile());
				}
			}
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return listR;
	}

	@Override
	public boolean insert(UserReservationVo vo) {
		boolean b = true;
		mapper.insert(vo);
		return b;
	}

	@Override
	public boolean updateRS(int orderCode) {
		boolean b = true;
		mapper.updateRS(orderCode);
		return b;
	}

	@Override
	public UserReservationVo viewDetail(int orderCode) {
		UserReservationVo vo = null;
		
		try {
			vo = mapper.viewDetail(orderCode);
			//System.out.println("sysFile"+vo.sysFile);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return vo;
	}

	@Override
	public boolean update(UserReservationVo vo) {
		boolean b = true;
		mapper.update(vo);
		return false;
	}

	@Override
	public boolean updateR(int orderCode) {
		boolean b = true;
		mapper.updateR(orderCode);
		return false;
	}

	@Override
	public List<UserReservationVo> selectReview(int userCode) {
		List<UserReservationVo> list = null;
		List<UserReservationVo> listR = new ArrayList<UserReservationVo>();
		
		//System.out.println("selectR userCode = " + userCode);
		try {
			list = mapper.selectReview1(userCode);
			if(list.size() > 0) {
				listR.add(list.get(0));
				int index = 0;
				for(int i = 0; i < list.size(); i++) {
					if(listR.get(index).getSno() != list.get(i).getSno()) {
						listR.add(list.get(i));
						index++;
					}
				}
				for(UserReservationVo vo : listR) {
					//System.out.println(vo.getSysFileC());
				}
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return listR;
	}
	
	@Override
	public List<UserReservationVo> selectRAnswer(int userCode){
		List<UserReservationVo> listRA = null;
		try {
			listRA = mapper.selectRAnswer(userCode);
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return listRA;
	}

	@Override
	public boolean modifyR(UserReservationVo vo) {
		boolean b = true;
		mapper.modifyR(vo);
		return b;
	}

	@Override
	public boolean deleteR(int orderCode) {
		boolean b = true;
		mapper.deleteR(orderCode);
		return b;
	}

	@Override
	public UserReservationVo selectReservation(int userCode, int itemCode) {
		UserReservationVo vo = null;
 		try {
			vo = mapper.selectReservation1(userCode, itemCode);
			//listI = mapper.selectReservation2(vo.itemCode,vo.siteCode);
			////System.out.println(listI.get(0).getSiteName());
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return vo;
	}

	@Override
	public List<UserReservationVo> selectReservation2(int itemCode, int siteCode) {
		List<UserReservationVo> list = null;
		
		
		
		try {
			list = mapper.selectReservation2(itemCode, siteCode);
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	@Override
	public UserReservationVo selectReservation3(int itemCode, int siteCode, int cUserCode) {
		UserReservationVo vo = null;
 		try {
			vo = mapper.selectReservation3(itemCode, siteCode, cUserCode);
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return vo;
	}

	@Override
	public boolean insertOrders(UserReservationVo vo) {
		boolean b = true;
		mapper.insertOrders(vo);
		return b;
	}

	@Override
	public UserReservationVo nextReservation(String orderCode2) {
		UserReservationVo vo = null;
		vo = mapper.nextReservation(orderCode2);
		return vo;
	}
	
	@Override
	public UserReservationVo nextReservation2(int itemCode) {
		UserReservationVo vo = null;
		vo = mapper.nextReservation2(itemCode);
		return vo;
	}
	

}
