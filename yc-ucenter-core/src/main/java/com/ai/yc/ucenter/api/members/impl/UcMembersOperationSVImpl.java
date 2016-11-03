package com.ai.yc.ucenter.api.members.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.yc.ucenter.api.members.interfaces.IUcMembersOperationSV;
import com.ai.yc.ucenter.api.members.param.UcMembersResponse;
import com.ai.yc.ucenter.api.members.param.checke.UcMembersCheckEmailRequest;
import com.ai.yc.ucenter.api.members.param.checke.UcMembersCheckeMobileRequest;
import com.ai.yc.ucenter.api.members.param.editemail.UcMembersEditEmailRequest;
import com.ai.yc.ucenter.api.members.param.editmobile.UcMembersEditMobileRequest;
import com.ai.yc.ucenter.api.members.param.editpass.UcMembersEditPassRequest;
import com.ai.yc.ucenter.api.members.param.get.UcMembersGetRequest;
import com.ai.yc.ucenter.api.members.param.get.UcMembersGetResponse;
import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginRequest;
import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginResponse;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersActiveRequest;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersGetOperationcodeRequest;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersGetOperationcodeResponse;
import com.ai.yc.ucenter.api.members.param.register.UcMembersRegisterRequest;
import com.ai.yc.ucenter.service.business.members.IUcMembersBusinessService;
import com.ai.yc.ucenter.service.business.members.IUcMembersOperationBusinessService;
import com.alibaba.dubbo.config.annotation.Service;

@Service
@Component
public class UcMembersOperationSVImpl implements IUcMembersOperationSV {
	@Autowired
	private IUcMembersOperationBusinessService iUcMembersOperationBusinessService;


	@Override
	public UcMembersGetOperationcodeResponse ucGetOperationcode(UcMembersGetOperationcodeRequest request)
			throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		return iUcMembersOperationBusinessService.saveOperationcode(request);
	}

	@Override
	public UcMembersResponse ucActiveMember(UcMembersActiveRequest request)
			throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		return iUcMembersOperationBusinessService.checkActiveMembe(request);
	}


	

}
