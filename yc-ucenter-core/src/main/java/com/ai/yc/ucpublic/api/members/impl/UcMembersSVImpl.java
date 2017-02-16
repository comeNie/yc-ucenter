package com.ai.yc.ucpublic.api.members.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.yc.ucenter.api.members.param.UcMembersResponse;
import com.ai.yc.ucenter.api.members.param.checke.UcMembersCheckEmailRequest;
import com.ai.yc.ucenter.api.members.param.checke.UcMembersCheckeMobileRequest;
import com.ai.yc.ucenter.api.members.param.del.UcMembersDelRequest;
import com.ai.yc.ucenter.api.members.param.editemail.UcMembersEditEmailRequest;
import com.ai.yc.ucenter.api.members.param.editmobile.UcMembersEditMobileRequest;
import com.ai.yc.ucenter.api.members.param.editpass.UcMembersEditPassRequest;
import com.ai.yc.ucenter.api.members.param.editusername.UcMembersEditUserNameRequest;
import com.ai.yc.ucenter.api.members.param.get.UcMembersGetRequest;
import com.ai.yc.ucenter.api.members.param.get.UcMembersGetResponse;
import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginRequest;
import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginResponse;
import com.ai.yc.ucenter.api.members.param.register.UcMembersRegisterRequest;
import com.ai.yc.ucenter.api.members.param.register.UcMembersRegisterResponse;
import com.ai.yc.ucenter.api.ucpubilc.interfaces.IUcPublicSV;
import com.ai.yc.ucenter.api.ucpubilc.param.PubResponse;
import com.ai.yc.ucenter.api.ucpubilc.param.UcCheckeEmailResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcCheckeMobilephoneResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcDelMemberResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcEditEmailResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcEditMobilephoneResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcEditPasswordResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcEditUserNameResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcGetMemberResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcLoginMemberResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcRegisterResp;
import com.ai.yc.ucpublic.service.business.members.IUcMembersBusinessService;
import com.alibaba.dubbo.config.annotation.Service;

@Service
@Component
public class UcMembersSVImpl implements IUcPublicSV {
	@Autowired
	private IUcMembersBusinessService iUcMembersBusinessService;

	@Override
	public PubResponse<UcLoginMemberResp> ucLoginMember(UcMembersLoginRequest request)
			throws BusinessException, SystemException {

		return iUcMembersBusinessService.loginMember(request);
	}

	@Override
	public PubResponse<UcRegisterResp> ucRegisterMember(UcMembersRegisterRequest request)
			throws BusinessException, SystemException {
	
		return iUcMembersBusinessService.insertMember(request);
	}

	@Override
	public PubResponse<UcGetMemberResp> ucGetMember(UcMembersGetRequest request) throws BusinessException, SystemException {
		
		return iUcMembersBusinessService.getMember(request);
	}

	@Override
	public PubResponse<UcEditMobilephoneResp> ucEditMobilephone(UcMembersEditMobileRequest request)
			throws BusinessException, SystemException {
	
		return iUcMembersBusinessService.updateMobilephone(request);
	}

	@Override
	public PubResponse<UcEditEmailResp> ucEditEmail(UcMembersEditEmailRequest request) throws BusinessException, SystemException {
	
		return iUcMembersBusinessService.updateEmail(request);
	}

	@Override
	public PubResponse<UcEditPasswordResp> ucEditPassword(UcMembersEditPassRequest request)
			throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		return iUcMembersBusinessService.updatePassword(request);
	}

	@Override
	public PubResponse<UcCheckeEmailResp> ucCheckeEmail(UcMembersCheckEmailRequest request)
			throws BusinessException, SystemException {
		return iUcMembersBusinessService.ucCheckeEmail(request);
	}

	@Override
	public PubResponse<UcCheckeMobilephoneResp> ucCheckeMobilephone(UcMembersCheckeMobileRequest request)
			throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		return iUcMembersBusinessService.ucCheckeMobilephone(request);
	}

	@Override
	public PubResponse<UcEditUserNameResp> ucEditUserName(UcMembersEditUserNameRequest request)
			throws BusinessException, SystemException {
		
		return iUcMembersBusinessService.ucEditUserName(request);
	}

	@Override
	public PubResponse<UcDelMemberResp> ucDelMember(UcMembersDelRequest request) throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		return iUcMembersBusinessService.ucDelMember(request);
	}

}
