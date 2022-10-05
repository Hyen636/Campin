package com.campin.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.campin.partner.SalesVo;

@Mapper
@Repository
public interface ManagerOrderMapper {
	public List<SalesVo> selectMonthSales(SalesVo vo);	
	public List<String> getOrderItemName(SalesVo vo);
}
