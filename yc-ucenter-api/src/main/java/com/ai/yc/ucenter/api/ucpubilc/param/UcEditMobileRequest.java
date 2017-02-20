package com.ai.yc.ucenter.api.ucpubilc.param;

import org.hibernate.validator.constraints.NotBlank;

import com.ai.opt.base.vo.BaseInfo;

/** 
 * @author  作者 “WTF” E-mail: 1031248990@qq.com
 * @date 创建时间：2017年2月16日 下午3:31:07 
 * @version 
 * @since  
 */
public class UcEditMobileRequest  extends BaseInfo {
	private static final long serialVersionUID = 1L;
	
	/**
     * 主键
     */

    private Integer uid;
    
    /**
     * 移动电话（可用于登录）
     */
	@NotBlank
    private String mobilephone;
    
    /**
     * 验证码
     */
	@NotBlank
    private String operationcode;
	
    /**
     * 国家代码
     */
	@NotBlank
	private String domain_name;

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

	public String getOperationcode() {
		return operationcode;
	}

	public void setOperationcode(String operationcode) {
		this.operationcode = operationcode;
	}

	public String getDomain_name() {
		return domain_name;
	}

	public void setDomain_name(String domain_name) {
		this.domain_name = domain_name;
	}
	
}
