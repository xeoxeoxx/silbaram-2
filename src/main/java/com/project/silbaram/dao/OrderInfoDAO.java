package com.project.silbaram.dao;

import com.project.silbaram.vo.OrderInfoVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderInfoDAO {

    void insertOrderInfo(OrderInfoVO orderInfoVO); //토스 저장용 구매 정보

    Long selectOid(String orderNum);



}
