package com.ai.yc.ucpublic.service.business.members;

import com.ai.yc.ucenter.api.members.param.opera.UcMembersActiveRequest;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersGetOperationcodeRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.PubResponse;
import com.ai.yc.ucenter.api.ucpubilc.param.UcActiveMemberResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcGetOperationcodeResp;

public interface IUcMembersOperationBusinessService {
	

	PubResponse<UcGetOperationcodeResp> saveOperationcode(UcMembersGetOperationcodeRequest request);

	PubResponse<UcActiveMemberResp> checkActiveMembe(UcMembersActiveRequest request);

	Integer processActivate(Integer uid, String validateCode, String operationtype, String string,String userinfo);
	
}
