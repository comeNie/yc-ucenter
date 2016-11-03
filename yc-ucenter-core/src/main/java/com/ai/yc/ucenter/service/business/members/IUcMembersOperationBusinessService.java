package com.ai.yc.ucenter.service.business.members;

import com.ai.yc.ucenter.api.members.param.UcMembersResponse;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersActiveRequest;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersGetOperationcodeRequest;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersGetOperationcodeResponse;

public interface IUcMembersOperationBusinessService {
	

	UcMembersGetOperationcodeResponse saveOperationcode(UcMembersGetOperationcodeRequest request);

	UcMembersResponse checkActiveMembe(UcMembersActiveRequest request);
	
}
