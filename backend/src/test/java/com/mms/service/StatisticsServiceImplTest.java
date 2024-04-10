package com.mms.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class StatisticsServiceImplTest {
	
//	@Autowired
//	private StatisticsService service;
//
//	@Test
//	void testSalesFunnel() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testConversionRate() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDealTrend() throws StreamWriteException, DatabindException, IOException {
//		List<Map<String,Object>> dealTrend = this.service.dealTrend("becky", null, null);
//		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.writerWithDefaultPrettyPrinter().writeValue(System.out, dealTrend);
//	}

}
