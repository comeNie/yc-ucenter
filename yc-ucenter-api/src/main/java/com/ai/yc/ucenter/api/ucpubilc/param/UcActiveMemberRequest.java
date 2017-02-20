package com.ai.yc.ucenter.api.ucpubilc.param;

import org.hibernate.validator.constraints.NotBlank;

import com.ai.opt.base.vo.BaseInfo;

/** 
 * @author  作者 “WTF” E-mail: 1031248990@qq.com
 * @date 创建时间：2017年2月16日 下午5:57:15 
 * @version 
 * @since  
 */
public class UcActiveMemberRequest  extends BaseInfo  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    /**
     * 用户id(必填)
     */

    private Integer uid;

    
    /**
     * 操作码激活码或验证码(必填)
     */
	@NotBlank
    private String operationcode;
    
    /**
     * 操作类型(必填)
			1：手机激活码
			2：手机验证码
			3：手机动态密码
			4：邮箱激活码
			5：邮箱验证码
			6：密码操作验证码
     */
	@NotBlank
    private String operationtype;
	
	@NotBlank
	private String userinfo;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getOperationcode() {
		return operationcode;
	}

	public void setOperationcode(String operationcode) {
		this.operationcode = operationcode;
	}

	public String getOperationtype() {
		return operationtype;
	}

	public void setOperationtype(String operationtype) {
		this.operationtype = operationtype;
	}

	public String getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(String userinfo) {
		this.userinfo = userinfo;
	}   
    

}
