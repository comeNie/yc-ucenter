package com.ai.yc.ucenter.api.ucpubilc.param;

import org.hibernate.validator.constraints.NotBlank;

import com.ai.opt.base.vo.BaseInfo;

/** 
 * @author  作者 “WTF” E-mail: 1031248990@qq.com
 * @date 创建时间：2017年2月16日 下午3:33:41 
 * @version 
 * @since  
 */
public class UcCheckeMobileRequest  extends BaseInfo {
	private static final long serialVersionUID = 1L;
	
	/**
     * 修改绑定操作传值，注册不传。
     */
    private Integer uid;
    
    /**
     * 移动电话（必填）
     */
    @NotBlank
//    @MobilePhone
    private String mobilephone;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
}
