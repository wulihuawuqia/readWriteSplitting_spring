package com.awifi.log.controller;

import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.awifi.log.model.OperLog;
import com.awifi.log.service.OperLogService;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping("log")
public class OperLogController {

	
	@Resource(name = "operLogService")
	private OperLogService operLogService;
	
    @RequestMapping(method = RequestMethod.POST, value = "/add", produces = "application/json")
    @ResponseBody
    public Map<String, Object> addOperLog(@RequestBody(required = true) Map<String, String> bodyParam, 
            HttpServletRequest request) {
    	OperLog obj = new OperLog();
		obj.setCompName("TEST");
    	try {
			operLogService.addOperLog(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    };
    
    @RequestMapping(method = RequestMethod.POST, value = "/get", produces = "application/json")
    @ResponseBody
    public Map<String, Object> getOperLog(HttpServletRequest request) {
    	try {
			operLogService.getOperLogList(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    };
}
