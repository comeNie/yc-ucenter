package com.ai.yc.ucpublic.service.business.members;

import com.ai.yc.ucenter.api.ucpubilc.param.PubResponse;
import com.ai.yc.ucenter.api.ucpubilc.param.UcActiveMemberRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.UcActiveMemberResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcGetOperationcodeRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.UcGetOperationcodeResp;

public interface IUcMembersOperationBusinessService {
	

	PubResponse<UcGetOperationcodeResp> saveOperationcode(UcGetOperationcodeRequest request);

	PubResponse<UcActiveMemberResp> checkActiveMembe(UcActiveMemberRequest request);

	Integer processActivate(Integer uid, String validateCode, String operationtype, String string,String userinfo);
	
}
