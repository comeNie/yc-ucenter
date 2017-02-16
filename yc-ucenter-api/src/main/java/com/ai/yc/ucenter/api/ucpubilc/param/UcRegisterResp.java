package com.ai.yc.ucenter.api.ucpubilc.param;
/** 
 * @author  作者 “WTF” E-mail: 1031248990@qq.com
 * @date 创建时间：2017年2月15日 下午4:47:06 
 * @version 
 * @since  
 */
public class UcRegisterResp {


	/**
     * 主键
     */
    private String uid;

    /**
     * 邮箱激活码
     */
    private String operationcode;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getOperationcode() {
		return operationcode;
	}

	public void setOperationcode(String operationcode) {
		this.operationcode = operationcode;
	}
}
