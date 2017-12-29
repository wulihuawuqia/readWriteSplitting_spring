package com.awifi.log.service.impl;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.awifi.log.model.OperLog;

@ContextConfiguration(locations = { "classpath:spring/spring-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class OperLogServiceImplTest extends AbstractJUnit4SpringContextTests {

	@Resource(name = "operLogService")
	private OperLogServiceImpl operLogServiceImpl;
	
	@Test
	public void testGetOperLogList() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		List<OperLog> list = operLogServiceImpl.getOperLogList(params);
		System.out.println(JSON.toJSONString(list));
	}

	@Test
	public void testAddOperLog() throws Exception {
		OperLog obj = new OperLog();
		obj.setCompName("TEST");
		operLogServiceImpl.addOperLog(obj);
		System.out.println(obj.getId());
	}

	@Test
	public void testUpdateOperLog() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteOperLog() {
		fail("Not yet implemented");
	}

}
