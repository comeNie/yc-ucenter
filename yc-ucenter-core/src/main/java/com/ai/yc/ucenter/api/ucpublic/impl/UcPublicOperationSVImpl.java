package com.ai.yc.ucenter.api.ucpublic.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.yc.ucenter.api.ucpubilc.interfaces.IUcPublicOperationSV;
import com.ai.yc.ucenter.api.ucpubilc.param.PubResponse;
import com.ai.yc.ucenter.api.ucpubilc.param.UcActiveMemberRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.UcActiveMemberResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcGetOperationcodeRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.UcGetOperationcodeResp;
import com.ai.yc.ucenter.service.business.members.IUcPublicOperationBusinessService;
import com.alibaba.dubbo.config.annotation.Service;

@Service
@Component
public class UcPublicOperationSVImpl implements IUcPublicOperationSV {
	@Autowired
	private IUcPublicOperationBusinessService iUcMembersOperationBusinessService;


	/**
	 * 操作码生成
	 */
	@Override
	public PubResponse<UcGetOperationcodeResp> ucGetOperationcode(UcGetOperationcodeRequest request)
			throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		return iUcMembersOperationBusinessService.saveOperationcode(request);
	}
	/**
	 * 操作码验证
	 */
	@Override
	public PubResponse<UcActiveMemberResp> ucActiveMember(UcActiveMemberRequest request)
			throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		return iUcMembersOperationBusinessService.checkActiveMembe(request);
	}




}
