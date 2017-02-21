package com.ai.yc.ucenter.api.ucpubilc.param;
/** 
 * @author  作者 “WTF” E-mail: 1031248990@qq.com
 * @date 创建时间：2017年2月15日 下午5:22:19 
 * @version 
 * @since  
 */
public class UcGetOperationcodeResp {

	/**
     * 用户id(只有手机/邮箱验证码和邮箱激活码用到，有值。)
     */
    private Integer uid;
    /**
     * 操作码
     */
	private String operationcode;

	public String getOperationcode() {
		return operationcode;
	}

	public void setOperationcode(String operationcode) {
		this.operationcode = operationcode;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}
}
