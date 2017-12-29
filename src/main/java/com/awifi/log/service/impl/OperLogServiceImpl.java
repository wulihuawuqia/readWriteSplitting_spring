/**
* 版权所有： 爱WiFi无线运营中心
* 创建日期:2017-12-27 11:25:03
* 创建作者：伍恰
* 文件名称：服务实现类
* 版本：  v1.0
* 功能：
* 修改记录：
*/
package com.awifi.log.service.impl;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.awifi.log.mapper.OperLogMapper;
import com.awifi.log.model.OperLog;
import com.awifi.log.service.OperLogService;

@Service("operLogService")
public class OperLogServiceImpl implements OperLogService {

    /**
     * 数据服务
     */
    @Autowired
    private OperLogMapper mapper;

    /** 日志  */
    protected static final Logger LOGGER = LoggerFactory.getLogger(OperLogServiceImpl.class);

    @Override
    public OperLog getOperLogById(Long id) throws Exception {
        return mapper.getOperLogById(id);
    }
    
    @Override
	public List<OperLog> getOperLogList(Map<String,Object> params) throws Exception {
        List<OperLog> list = null;
        try {
            list = mapper.getOperLogListByParams(params);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        System.out.println(list);
        return list;
    }
    
    @Override
    public void addOperLog(OperLog obj) throws Exception {
        try {
            mapper.addOperLog(obj);
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
    
    @Override
    public void updateOperLog(OperLog obj) throws Exception {
        try {
            mapper.updateOperLog(obj);
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
    
    @Override
    public void deleteOperLog(Long id) throws Exception {
        try {
            mapper.deleteOperLog(id);
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
    
}
