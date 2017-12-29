/**
* 版权所有： 爱WiFi无线运营中心
* 创建日期:2017-12-27 11:25:03
* 创建作者：伍恰
* 文件名称：实体类: np_biz_oper_log - 
* 版本：  v1.0
* 功能：
* 修改记录：
*/
package com.awifi.log.model;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>实体类</p>
 * <p>Table: np_biz_oper_log - </p>
 * @since 2017-12-27 11:25:03
 */
public class OperLog implements Serializable {
	
    private static final long serialVersionUID =1;
    /** id - 主键id */
    private Integer id;

    /** 对象名称 */
    private String compName;
    /** 模块名称 */
    private String moduleName;
    /** 操作详情 */
    private String detail;
    /** 操作用户 */
    private String userName;
    /** 创建日期 */
    private Date createTime;
    /** 用户id */
    private Integer userId;
    /** ip */
    private String ip;


    public Integer getId(){
        return this.id;
    }
    public void setId(Integer id){
        this.id = id;
    }

    public String getCompName(){
        return this.compName;
    }
    public void setCompName(String compName){
        this.compName = compName;
    }

    public String getModuleName(){
        return this.moduleName;
    }
    public void setModuleName(String moduleName){
        this.moduleName = moduleName;
    }

    public String getDetail(){
        return this.detail;
    }
    public void setDetail(String detail){
        this.detail = detail;
    }

    public String getUserName(){
        return this.userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }

    public Date getCreateTime(){
        return this.createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    public Integer getUserId(){
        return this.userId;
    }
    public void setUserId(Integer userId){
        this.userId = userId;
    }

    public String getIp(){
        return this.ip;
    }
    public void setIp(String ip){
        this.ip = ip;
    }
}