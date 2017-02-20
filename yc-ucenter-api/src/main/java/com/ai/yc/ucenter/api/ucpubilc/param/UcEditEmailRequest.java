package com.ai.yc.ucenter.api.ucpubilc.param;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.ai.opt.base.vo.BaseInfo;

/** 
 * @author  作者 “WTF” E-mail: 1031248990@qq.com
 * @date 创建时间：2017年2月16日 下午3:31:29 
 * @version 
 * @since  
 */
public class UcEditEmailRequest extends BaseInfo  {
private static final long serialVersionUID = 1L;
	
	/**
     * 主键
     */
    private Integer uid;
    
    /**
     * 邮箱
     */
    @NotBlank
    @Email
    private String email;
    
    /**
     * 验证码
     */
    @NotBlank
    private String operationcode;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOperationcode() {
		return operationcode;
	}

	public void setOperationcode(String operationcode) {
		this.operationcode = operationcode;
	}
}
