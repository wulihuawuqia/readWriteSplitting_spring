/**
* 版权所有： 爱WiFi无线运营中心
* 创建日期:2017-12-27 11:25:03
* 创建作者：伍恰
* 文件名称：数据接口实现类
* 版本：  v1.0
* 功能：
* 修改记录：
*/
package com.awifi.log.mapper;

import java.util.List;
import java.util.Map;

import com.awifi.log.model.OperLog;

public interface OperLogMapper {

    /**
     * 根据id 查询详情
     * @param id id 
     * @return 信息
     * @throws Exception 异常/参数
     * @author 伍恰  
     * @date 2017-12-27 11:25:03
     */
    OperLog getOperLogById(Long id) throws Exception ;
    
    /**
     * 根据条件查询 总数
     * @param params 参数
     * @return 总数
     * @throws Exception 异常/参数
     * @author 伍恰  
     * @date 2017-12-27 11:25:03
     */
    Integer getCountByParams(Map<String,Object> params) throws Exception ;
    
    /**
     * 根据条件查询列表
     * @param params 参数
     * @return 数组
     * @throws Exception 异常/参数
     * @author 伍恰  
     * @date 2017年10月27日 上午10:45:15
     */
    List<OperLog> getOperLogListByParams(Map<String,Object> params) throws Exception ;
	
	/**
     * 新增
     * @param obj 信息
     * @throws Exception 异常/参数
     * @author 伍恰  
     * @date 2017年10月27日 上午10:46:14
     */
    void addOperLog(OperLog obj) throws Exception ;
	
	/**
     * 修改
     * @param obj 信息
     * @throws Exception 异常/参数
     * @author 伍恰  
     * @date 2017年10月27日 上午10:46:14
     */
    void updateOperLog(OperLog obj) throws Exception ;
	
	/**
     * 按 id 删除
     * @param id id 
     * @throws Exception 异常/参数
     * @author 伍恰  
     * @date 2017年10月27日 上午10:47:04
     */
    void deleteOperLog(Long id) throws Exception ;
}