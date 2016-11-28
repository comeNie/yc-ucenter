package com.ai.yc.ucenter.service.business.members;

import com.ai.yc.ucenter.api.members.param.UcMembersResponse;
import com.ai.yc.ucenter.api.members.param.checke.UcMembersCheckEmailRequest;
import com.ai.yc.ucenter.api.members.param.checke.UcMembersCheckeMobileRequest;
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
import com.ai.yc.ucenter.dao.mapper.bo.UcMembers;

public interface IUcMembersBusinessService {

	UcMembersLoginResponse loginMember(UcMembersLoginRequest request);

	UcMembersRegisterResponse insertMember(UcMembersRegisterRequest request);

	UcMembersGetResponse getMember(UcMembersGetRequest request);

	UcMembersResponse updateMobilephone(UcMembersEditMobileRequest request);

	UcMembersResponse updateEmail(UcMembersEditEmailRequest request);

	UcMembersResponse updatePassword(UcMembersEditPassRequest request);

	UcMembersResponse ucCheckeEmail(UcMembersCheckEmailRequest request);

	UcMembersResponse ucCheckeMobilephone(UcMembersCheckeMobileRequest request);

	UcMembers getUcMembers(UcMembersLoginRequest request);

	UcMembersResponse ucEditUserName(UcMembersEditUserNameRequest request);

}
