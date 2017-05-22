
package com.tigerj.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tigerj.domain.City;
import com.tigerj.mapper.CityMapper;

/**
 * Integration tests for {@link CityMapper}.
 *
 * @author shihu.jiang
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CityIntegrationTests {
	
	@Autowired
	private CityMapper cityMapper;
	
	@Test
	public void findByStateTest(){
		List<City> citys = cityMapper.findByState("FL");
		assertTrue(citys!=null && citys.size()==4);
	}
	
}
