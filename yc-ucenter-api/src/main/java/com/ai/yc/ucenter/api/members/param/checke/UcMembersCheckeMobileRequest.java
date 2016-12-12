package com.ai.yc.ucenter.api.members.param.checke;

import org.hibernate.validator.constraints.NotBlank;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.opt.validator.constraints.MobilePhone;

public class UcMembersCheckeMobileRequest extends BaseInfo{
	
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
