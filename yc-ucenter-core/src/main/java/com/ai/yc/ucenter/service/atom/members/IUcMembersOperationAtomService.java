package com.ai.yc.ucenter.service.atom.members;


import com.ai.yc.ucenter.api.members.param.opera.UcMembersActiveRequest;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersGetOperationcodeRequest;


public interface IUcMembersOperationAtomService {
	String saveOperationcode(UcMembersGetOperationcodeRequest request);

	int  getActiveMembe(UcMembersActiveRequest request);
 
    
}
