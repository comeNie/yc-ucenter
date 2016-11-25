
package com.ai.yc.ucenter.util;

import java.io.File;
import java.io.IOException;
import java.util.Map;


import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.DefaultResourceLoader;

import com.google.common.collect.Maps;

/**
 * 全局配置类
 * @author mengbo
 * @version 2016-11-21
 */
public class Global {

	/**
	 * 当前对象实例
	 */
	private static Global global = new Global();
	
	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = Maps.newHashMap();
	/**
	 * 保存全局属性值
	 */
	private static Map<String, Long> mapLong = Maps.newHashMap();	

	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader loader = new PropertiesLoader("/context/uc.properties");

	
	/**
	 * 获取当前对象实例
	 */
	public static Global getInstance() {
		return global;
	}
	
	/**
	 * 获取string类型配置 
	 * @see 
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null){
			value = loader.getProperty(key);
			map.put(key, value != null ? value : StringUtils.EMPTY);
		}
		return value;
	}
	
	
	/**
	 * 获取string类型配置 
	 * @see 
	 */
	public static Long getLong(String key) {
		Long value = mapLong.get(key);
		if (value == null){
			String ss = loader.getProperty(key);
			mapLong.put(key, ss != null ? Long.valueOf(ss) : null);
		}
		return value;
	}

	
	
	/**
	 * 获取用户名随机数长度
	 */
	public static String getUsernamLength() {
		return getConfig("username.length");
	}
	
	/**
	 * 获取用户名前缀
	 */
	public static String getUsernamPrefix() {
		return getConfig("username.prefix");
	}
	
	/**
	 * 获取手机激活码或动态密码30分钟有效
	 */
	public static String getMobilActiveValid() {
	
		
		return getConfig("mobil.active.valid");
	}

	
	/**
	 * 验证码60分钟有效
	 */
	public static String getVerifValid() {
		return getConfig("verif.valid");
	}
	
	/**
	 * 60秒再次获取
	 */
	public static String getCodeAgain() {
		return getConfig("get_again");
	}
    /**
     * 获取工程路径
     * @return
     */
    public static String getProjectPath(){
    	// 如果配置了工程路径，则直接返回，否则自动获取。
		String projectPath = Global.getConfig("projectPath");
		if (StringUtils.isNotBlank(projectPath)){
			return projectPath;
		}
		try {
			File file = new DefaultResourceLoader().getResource("").getFile();
			if (file != null){
				while(true){
					File f = new File(file.getPath() + File.separator + "src" + File.separator + "main");
					if (f == null || f.exists()){
						break;
					}
					if (file.getParentFile() != null){
						file = file.getParentFile();
					}else{
						break;
					}
				}
				projectPath = file.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return projectPath;
    }
	
}
