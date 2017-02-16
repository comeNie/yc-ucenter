package com.ai.yc.ucpublic.api.members.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersActiveRequest;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersGetOperationcodeRequest;
import com.ai.yc.ucenter.api.ucpubilc.interfaces.IUcPublicOperationSV;
import com.ai.yc.ucenter.api.ucpubilc.param.PubResponse;
import com.ai.yc.ucenter.api.ucpubilc.param.UcActiveMemberResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcGetOperationcodeResp;
import com.ai.yc.ucpublic.service.business.members.IUcMembersOperationBusinessService;
import com.alibaba.dubbo.config.annotation.Service;

@Service
@Component
public class UcMembersOperationSVImpl implements IUcPublicOperationSV {
	@Autowired
	private IUcMembersOperationBusinessService iUcMembersOperationBusinessService;


	/**
	 * 操作码生成
	 */
	@Override
	public PubResponse<UcGetOperationcodeResp> ucGetOperationcode(UcMembersGetOperationcodeRequest request)
			throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		return iUcMembersOperationBusinessService.saveOperationcode(request);
	}
	/**
	 * 操作码验证
	 */
	@Override
	public PubResponse<UcActiveMemberResp> ucActiveMember(UcMembersActiveRequest request)
			throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		return iUcMembersOperationBusinessService.checkActiveMembe(request);
	}



}
