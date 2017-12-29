package com.awifi.proxy;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.ReflectionUtils;

/**
 * 使用Spring的动态数据源，需要实现AbstractRoutingDataSource
 * 使用determiniCurrentLookupKey方法拿到识别key来判断读/写数据源
 * token显然是多例的，所以引入ThreadLocal保存
* @ClassName: DynamicDataSource
* @Description: TODO(这里用一句话描述这个类的作用)
* @author wuqia
* @date 2017年12月27日 上午10:16:01
*
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	/*
	 * 读库总数
	 */
	private Integer slaveCount;
	
	/*
	 * 读库轮询计数，初始为-1，本类为单例，AtomicInteger线程安全
	 */
	private AtomicInteger counter = new AtomicInteger(-1);
	
	/**
	 * 存储读库的识别key slave01，slave02... 写库识别key 为master
	 */
	private List<Object> slaveDataSources = new ArrayList<Object>();
	
	/**
	 * 当前线程的写库/读库 token
	 */
	private static final ThreadLocal<Boolean> tokenHolder = new ThreadLocal<Boolean>();
	
	public static void markMaster(boolean isMaster) {
		tokenHolder.set(isMaster);
	}
	
	@Override
	protected Object determineCurrentLookupKey() {
		if (tokenHolder.get()) {
			return "master" ;//写库
		}
		//轮询读库，得到下标为：0,1,2......
		Integer index = counter.incrementAndGet() % slaveCount;
		if(counter.get() > slaveDataSources.size()) {
			counter.set(-1);
		}
		return slaveDataSources.get(index);
	}
	
	@Override
	public void afterPropertiesSet() {
		super.afterPropertiesSet();
		//父类的resolvedDataSoureces 属性是private ，需要使用反射获取
		Field field = ReflectionUtils.findField(AbstractRoutingDataSource.class, "resolvedDataSources");
		field.setAccessible(true);//设置可访问
		try {
			Map<Object, DataSource> resolvedDataSource = (Map<Object, DataSource>) field.get(this);
			//读库数等于dataSource总数减写库数
			this.slaveCount = resolvedDataSource.size() - 1;
			for (Map.Entry<Object, DataSource> entry : resolvedDataSource.entrySet()) {
				if("master".equals(entry.getKey())) {
					continue;
				}
				slaveDataSources.add(entry.getKey());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
