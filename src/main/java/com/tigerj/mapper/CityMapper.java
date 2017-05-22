package com.tigerj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tigerj.domain.City;

@Mapper
public interface CityMapper {

	@Select("SELECT * FROM CITY WHERE state = #{state}")
	List<City> findByState(@Param("state") String state);
	

}
