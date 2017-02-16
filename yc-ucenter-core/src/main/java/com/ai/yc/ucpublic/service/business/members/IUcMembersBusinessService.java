package com.ai.yc.ucpublic.service.business.members;

import com.ai.yc.ucenter.api.members.param.checke.UcMembersCheckEmailRequest;
import com.ai.yc.ucenter.api.members.param.checke.UcMembersCheckeMobileRequest;
import com.ai.yc.ucenter.api.members.param.del.UcMembersDelRequest;
import com.ai.yc.ucenter.api.members.param.editemail.UcMembersEditEmailRequest;
import com.ai.yc.ucenter.api.members.param.editmobile.UcMembersEditMobileRequest;
import com.ai.yc.ucenter.api.members.param.editpass.UcMembersEditPassRequest;
import com.ai.yc.ucenter.api.members.param.editusername.UcMembersEditUserNameRequest;
import com.ai.yc.ucenter.api.members.param.get.UcMembersGetRequest;
import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginRequest;
import com.ai.yc.ucenter.api.members.param.register.UcMembersRegisterRequest;
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

public interface IUcMembersBusinessService {

	PubResponse<UcLoginMemberResp> loginMember(UcMembersLoginRequest request);

	PubResponse<UcRegisterResp> insertMember(UcMembersRegisterRequest request);

	PubResponse<UcGetMemberResp> getMember(UcMembersGetRequest request);

	PubResponse<UcEditMobilephoneResp> updateMobilephone(UcMembersEditMobileRequest request);

	PubResponse<UcEditEmailResp> updateEmail(UcMembersEditEmailRequest request);

	PubResponse<UcEditPasswordResp> updatePassword(UcMembersEditPassRequest request);

	PubResponse<UcCheckeEmailResp> ucCheckeEmail(UcMembersCheckEmailRequest request);

	PubResponse<UcCheckeMobilephoneResp> ucCheckeMobilephone(UcMembersCheckeMobileRequest request);

//	UcMembers getUcMembers(UcMembersLoginRequest request);

	PubResponse<UcEditUserNameResp> ucEditUserName(UcMembersEditUserNameRequest request);

	PubResponse<UcDelMemberResp> ucDelMember(UcMembersDelRequest request);

}
