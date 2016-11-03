package com.ai.yc.ucenter.api.members.param.get;

import java.io.Serializable;

/**
 * 用户信息获取方式枚举类<br>
 * Date: 2016年11月1日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 *
 * @author mengbo
 */
public enum UcMembersGetModeEnum implements Serializable{
	 /**  
     * 用户id
     */
    USERID_ENUM("1"), 
	/**
     * 邮箱
     */
    EMAIL_ENUM("2"),
	/**
     * 手机
     */
    MOBILE_ENUM("3"),
    /**
     * 用户名
     */
    USERNAME_MODE("4");
   

    private String levelValue;

	UcMembersGetModeEnum(String value) {
        this.levelValue = value;
    }

    public String getLevelValue() {
        return levelValue;
    }
}
