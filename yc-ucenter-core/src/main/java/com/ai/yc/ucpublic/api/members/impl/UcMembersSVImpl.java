package com.ai.yc.ucpublic.api.members.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.yc.ucenter.api.ucpubilc.interfaces.IUcPublicSV;
import com.ai.yc.ucenter.api.ucpubilc.param.PubResponse;
import com.ai.yc.ucenter.api.ucpubilc.param.UcCheckEmailRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.UcCheckeEmailResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcCheckeMobileRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.UcCheckeMobilephoneResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcDelMemberResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcDelRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.UcEditEmailRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.UcEditEmailResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcEditMobileRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.UcEditMobilephoneResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcEditPassRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.UcEditPasswordResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcEditUserNameRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.UcEditUserNameResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcGetMemberRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.UcGetMemberResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcLoginMemberRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.UcLoginMemberResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcRegisterRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.UcRegisterResp;
import com.ai.yc.ucpublic.service.business.members.IUcMembersBusinessService;
import com.alibaba.dubbo.config.annotation.Service;

@Service
@Component
public class UcMembersSVImpl implements IUcPublicSV {
	@Autowired
	private IUcMembersBusinessService iUcMembersBusinessService;

	@Override
	public PubResponse<UcLoginMemberResp> ucLoginMember(UcLoginMemberRequest request)
			throws BusinessException, SystemException {
		return iUcMembersBusinessService.loginMember(request);
	}

	@Override
	public PubResponse<UcRegisterResp> ucRegisterMember(UcRegisterRequest request)
			throws BusinessException, SystemException {
		return iUcMembersBusinessService.insertMember(request);
	}

	@Override
	public PubResponse<UcGetMemberResp> ucGetMember(UcGetMemberRequest request)
			throws BusinessException, SystemException {
		return iUcMembersBusinessService.getMember(request);
	}

	@Override
	public PubResponse<UcEditMobilephoneResp> ucEditMobilephone(UcEditMobileRequest request)
			throws BusinessException, SystemException {
		return iUcMembersBusinessService.updateMobilephone(request);
	}

	@Override
	public PubResponse<UcEditEmailResp> ucEditEmail(UcEditEmailRequest request)
			throws BusinessException, SystemException {
		return iUcMembersBusinessService.updateEmail(request);
	}

	@Override
	public PubResponse<UcEditPasswordResp> ucEditPassword(UcEditPassRequest request)
			throws BusinessException, SystemException {
		return iUcMembersBusinessService.updatePassword(request);
	}

	@Override
	public PubResponse<UcCheckeEmailResp> ucCheckeEmail(UcCheckEmailRequest request)
			throws BusinessException, SystemException {
		return iUcMembersBusinessService.ucCheckeEmail(request);
	}

	@Override
	public PubResponse<UcCheckeMobilephoneResp> ucCheckeMobilephone(UcCheckeMobileRequest request)
			throws BusinessException, SystemException {
		return iUcMembersBusinessService.ucCheckeMobilephone(request);
	}

}
