package com.awifi.proxy;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.ReflectionUtils;

public class DataSourceAspect {
	
	private List<String> slaveMethodPattern = new ArrayList<String>(); //保存有readonly属性的带通配符的方法
	private static final String[] defaultSlaveMethodStartWith = new String[]{"query","find","get"};
	private String[] slaveMethodStartWith; //保存有slaveMethodStartWith属性的方法名头部
	
	public void setTxAdvice(TransactionInterceptor txAdvice) throws Exception {
		if(txAdvice == null) {
			//没有配置事务策略
			return ;
		}
		
		//从txAdvice获取策略配置信息
		TransactionAttributeSource transactionAttributeSource = txAdvice.getTransactionAttributeSource();
		if(!(transactionAttributeSource instanceof NameMatchTransactionAttributeSource)) {
			return ;
		}
		
		//使用反射获取NameMatchTransactionAttributeSource 对象中的属性值
		NameMatchTransactionAttributeSource matchTransactionAttributeSource = (NameMatchTransactionAttributeSource)transactionAttributeSource;
		
		Field nameMapField = ReflectionUtils.findField(NameMatchTransactionAttributeSource.class, "nameMap");
		
		nameMapField.setAccessible(true);//设置字段可访问
		
		//获取nameMap的值
		Map<String, TransactionAttribute> map = (Map<String, TransactionAttribute>) nameMapField.get(matchTransactionAttributeSource);
		
		//遍历nameMap
		for (Map.Entry<String, TransactionAttribute> entry : map.entrySet()) {
			if(!entry.getValue().isReadOnly()) {
				continue;
			}
			slaveMethodPattern.add(entry.getKey());
		}
		
	}
	
	/**
	 * 
	* @Title: before
	* @Description: 切面before 方法
	* @param point  参数描述
	* @return void 返回类型描述
	* @throws
	* @data 2017年12月27日 上午9:16:03
	* @author wuqia
	 */
	public void before(JoinPoint point) {
		//获取当前执行的方法名
		String methodName = point.getSignature().getName();
		
		boolean isSlave = false;
		
		if (slaveMethodPattern.isEmpty()) {
			//没有配置read-only属性，采用方法名匹配方式
			isSlave = isSlaveByMethodName(methodName);
		} else {
			//配置read-only属性，采用通配符匹配
			for(String mappedName : slaveMethodPattern) {
				if(isSlaveByConfigWildcard(methodName, mappedName)) {
					isSlave = true;
					break;
				}
			}
		}
		
		DynamicDataSource.markMaster(!isSlave);
		
	}
	
	/**
	*
	* @Title: isSlaveByMethodName
	* @Description: 匹配以指定名称开头的方法名，配置slaveMethodStartWith属性，或使用默认
	* @param methodName
	* @return  参数描述
	* @return Boolean 返回类型描述
	* @throws
	* @data 2017年12月27日 上午9:25:58
	* @author wuqia
	 */
	private Boolean isSlaveByMethodName(String methodName) {
		return StringUtils.startsWithAny(methodName, getSlaveMethodStartWith());
	}

	/**
	 * 
	* @Title: isSlaveByConfigWildcard
	* @Description: 匹配带通配符"xxx*","*xxx"和"*xxx*"的方法名，源自配置了readonly属性的方法名
	* @param methodName
	* @param mappedName
	* @return  参数描述
	* @return Boolean 返回类型描述
	* @throws
	* @data 2017年12月27日 上午9:30:53
	* @author wuqia
	 */
	protected Boolean isSlaveByConfigWildcard(String methodName, String mappedName) {
		return PatternMatchUtils.simpleMatch(mappedName, methodName);
	}
	
	/**
	 * 
	* @Title: getSlaveMethodStartWith
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @return  参数描述
	* @return String[] 返回类型描述
	* @throws
	* @data 2017年12月27日 上午9:27:54
	* @author wuqia
	 */
	public String[] getSlaveMethodStartWith() {
		if(slaveMethodStartWith == null) {
			return defaultSlaveMethodStartWith;
		}
		return slaveMethodStartWith;
	}

	public void setSlaveMethodStartWith(String[] slaveMethodStartWith) {
		this.slaveMethodStartWith = slaveMethodStartWith;
	}
	
	
}
