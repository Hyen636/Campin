package com.campin.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.campin.partner.PartnerOrderPage;
import com.campin.partner.PartnerOrderVo;
import com.campin.partner.SalesVo;

@Mapper
@Repository
public interface PartnerOrderMapper {
	// 예약 조회
	public List<PartnerOrderVo> selectCalendar(PartnerOrderVo vo);
	public List<PartnerOrderVo> selectDate(PartnerOrderPage page);
	public int totSize(PartnerOrderPage page);
	public PartnerOrderVo selectOrderDetail(int orderCode);
	public int orderConfirm(int orderCode);
	public int orderCancel(int orderCode);
	public int visitCompleted(String nowDate);
	public int autoCancel(String nowDate);
	
	// 매출 조회
	public List<SalesVo> selectSales(SalesVo vo);
	public List<SalesVo> selectMonthSales(SalesVo vo);	
}
