package com.ai.yc.ucpublic.service.atom.members;


import java.util.List;

import com.ai.yc.ucenter.api.members.param.opera.UcMembersActiveRequest;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersGetOperationcodeRequest;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembersOperation;


public interface IUcMembersOperationAtomService {
	String saveOperationcode(UcMembersGetOperationcodeRequest request);

	List<UcMembersOperation>  getActiveMembe(UcMembersActiveRequest request);

	int updateActiveMember(UcMembersActiveRequest request);
	public UcMembersOperation lastTimeOperation(Integer uid,String operationtype);
	 public  Integer processActivate(Integer uid , String validateCode,String operationtype, String string,String userinfo);
}
