package com.campin.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import com.campin.mybatis.ManagerOrderMapper;
import com.campin.partner.SalesVo;

@Service
@Transactional
public class ManagerOrderService {
	
	@Autowired
	ManagerOrderMapper mapper;
	@Autowired
	DataSourceTransactionManager transaction;
	TransactionStatus status;
	/*
	public List<SalesVo> selectSales(SalesVo vo){
   		List<SalesVo> list = null;
   		list = mapper.selectSales(vo);
   		return list;
   	}*/
	
	public List<SalesVo> selectMonthSales(SalesVo vo){
   		List<SalesVo> list = null;
   		list = mapper.selectMonthSales(vo);
   		return list;
   	}
	
	public List<String> getOrderItemName(SalesVo vo){
		List<String> orderList = null;
		orderList = mapper.getOrderItemName(vo);
		return orderList;
	}
}
