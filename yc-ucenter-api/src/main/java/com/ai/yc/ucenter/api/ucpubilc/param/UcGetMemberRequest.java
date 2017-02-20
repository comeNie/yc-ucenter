package com.ai.yc.ucenter.api.ucpubilc.param;

import org.hibernate.validator.constraints.NotBlank;

import com.ai.opt.base.vo.BaseInfo;

/**
 * @author 作者 “WTF” E-mail: 1031248990@qq.com
 * @date 创建时间：2017年2月16日 下午3:30:51
 * @version
 * @since
 */
public class UcGetMemberRequest extends BaseInfo {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户名称
	 */
	@NotBlank
	private String username;
	/**
	 * 获取方式 1：用户id 2：邮箱 3：手机 4：用户名
	 */
	@NotBlank
	private String getmode;

	private String userId;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGetmode() {
		return getmode;
	}

	public void setGetmode(String getmode) {
		this.getmode = getmode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
