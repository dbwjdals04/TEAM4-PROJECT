package com.bit.academy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface OrderMapper {

    Map<String, Object> selectAllOrderData();

}
