/**
* 版权所有： 爱WiFi无线运营中心
* 创建日期:2017-12-27 11:25:03
* 创建作者：伍恰
* 文件名称：服务接口
* 版本：  v1.0
* 功能：
* 修改记录：
*/
package com.awifi.log.service;
import java.util.List;
import java.util.Map;
import com.awifi.log.model.OperLog;

public interface OperLogService {
    
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
     * 根据条件查询
     * @param page 分页实体
     * @param params 参数
     * @throws Exception 异常/参数
     * @author 伍恰  
     * @date 2017-12-27 11:25:03
     */
    List<OperLog> getOperLogList(Map<String,Object> params) throws Exception ;
    
    /**
     * 新增
     * @param obj 
     * @throws Exception 异常/参数
     * @author 伍恰  
     * @date 2017-12-27 11:25:03
     */
    void addOperLog(OperLog obj) throws Exception ;
    
    /**
     * 修改
     * @param obj 
     * @throws Exception 异常/参数
     * @author 伍恰  
     * @date 2017-12-27 11:25:03
     */
    void updateOperLog(OperLog obj) throws Exception ;
    
    /**
     * 根据id 删除
     * @param id id
     * @throws Exception 异常/参数
     * @author 伍恰  
     * @date 2017-12-27 11:25:03
     */
    void deleteOperLog(Long id) throws Exception ;
}
