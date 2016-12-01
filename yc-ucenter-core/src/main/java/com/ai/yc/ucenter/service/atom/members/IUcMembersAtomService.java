package com.ai.yc.ucenter.service.atom.members;

import java.util.List;

import com.ai.yc.ucenter.api.members.param.UcMembersResponse;
import com.ai.yc.ucenter.api.members.param.checke.UcMembersCheckEmailRequest;
import com.ai.yc.ucenter.api.members.param.checke.UcMembersCheckeMobileRequest;
import com.ai.yc.ucenter.api.members.param.editemail.UcMembersEditEmailRequest;
import com.ai.yc.ucenter.api.members.param.editmobile.UcMembersEditMobileRequest;
import com.ai.yc.ucenter.api.members.param.get.UcMembersGetRequest;
import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginRequest;
import com.ai.yc.ucenter.api.members.param.register.UcMembersRegisterRequest;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembers;

public interface IUcMembersAtomService {

 

	List<UcMembers> loginMember(String username, String passMd5, String loginmode);

	String insertMember(UcMembersRegisterRequest request) throws Exception;

	String insertMemberPo(UcMembers ucMembers) ;
	
	List<UcMembers> getMember(UcMembersGetRequest request);

	int updateMobilephone(UcMembersEditMobileRequest request);

	int updateEmail(UcMembersEditEmailRequest request);

	int updatePassword(UcMembers ucMembers);

	int checkEmail(UcMembersCheckEmailRequest request);

	int checkMobilephone(UcMembersCheckeMobileRequest request);

	UcMembers getSalt(UcMembersLoginRequest request) ;

	com.ai.yc.ucenter.dao.mapper.bo.UcMembers getUcMembersbyUid(Integer uid);
	public String getUsername();

	Integer updateUserName(Integer uid, String username);

	List<UcMembers> getMemberByUsername(String username);

	int delMember(Integer uid);

 
    
}
