package com.ai.yc.ucenter.api.members.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.yc.ucenter.api.members.interfaces.IYCUcMembersSV;
import com.ai.yc.ucenter.api.members.param.UcMembersInfo;
import com.ai.yc.ucenter.api.members.param.UcMembersVo;
import com.ai.yc.ucenter.service.business.members.IUcMembersBusinessService;
import com.ai.yc.ucenter.service.business.members.IYcUcMemberBusiSV;
import com.alibaba.dubbo.config.annotation.Service;
@Service
@Component
public class YcUcMembersSVImpl implements IYCUcMembersSV {
	@Autowired
	private IYcUcMemberBusiSV YcUcMemberBusiSV;
	@Override
	public UcMembersInfo queryUcMember(Integer userID) throws BusinessException,
			SystemException {
		return YcUcMemberBusiSV.QueryUcMember(userID);
	}

}
