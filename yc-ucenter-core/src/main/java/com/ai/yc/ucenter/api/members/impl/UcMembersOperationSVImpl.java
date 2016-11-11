package com.ai.yc.ucenter.api.members.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.yc.ucenter.api.members.interfaces.IUcMembersOperationSV;
import com.ai.yc.ucenter.api.members.param.UcMembersResponse;

import com.ai.yc.ucenter.api.members.param.opera.UcMembersActiveRequest;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersGetOperationcodeRequest;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersGetOperationcodeResponse;

import com.ai.yc.ucenter.service.business.members.IUcMembersOperationBusinessService;
import com.alibaba.dubbo.config.annotation.Service;

@Service
@Component
public class UcMembersOperationSVImpl implements IUcMembersOperationSV {
	@Autowired
	private IUcMembersOperationBusinessService iUcMembersOperationBusinessService;


	/**
	 * 操作码生成
	 */
	@Override
	public UcMembersGetOperationcodeResponse ucGetOperationcode(UcMembersGetOperationcodeRequest request)
			throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		return iUcMembersOperationBusinessService.saveOperationcode(request);
	}
	/**
	 * 操作码验证
	 */
	@Override
	public UcMembersResponse ucActiveMember(UcMembersActiveRequest request)
			throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		return iUcMembersOperationBusinessService.checkActiveMembe(request);
	}



}
