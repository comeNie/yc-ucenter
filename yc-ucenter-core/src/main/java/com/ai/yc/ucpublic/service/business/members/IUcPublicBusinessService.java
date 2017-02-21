package com.ai.yc.ucpublic.service.business.members;

import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginRequest;
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
import com.ai.yc.ucenter.dao.mapper.bo.UcMembers;

public interface IUcPublicBusinessService {

	PubResponse<UcLoginMemberResp> loginMember(UcLoginMemberRequest request);

	PubResponse<UcRegisterResp> insertMember(UcRegisterRequest request);

	PubResponse<UcGetMemberResp> getMember(UcGetMemberRequest request);

	PubResponse<UcEditMobilephoneResp> updateMobilephone(UcEditMobileRequest request);

	PubResponse<UcEditEmailResp> updateEmail(UcEditEmailRequest request);

	PubResponse<UcEditPasswordResp> updatePassword(UcEditPassRequest request);

	PubResponse<UcCheckeEmailResp> ucCheckeEmail(UcCheckEmailRequest request);

	PubResponse<UcCheckeMobilephoneResp> ucCheckeMobilephone(UcCheckeMobileRequest request);

	UcMembers getUcMembers(UcMembersLoginRequest request);

}
