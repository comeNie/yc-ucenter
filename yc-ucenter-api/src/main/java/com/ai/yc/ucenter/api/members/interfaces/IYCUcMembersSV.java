package com.ai.yc.ucenter.api.members.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.yc.ucenter.api.members.param.UcMembersInfo;
import com.ai.yc.ucenter.api.members.param.UcMembersVo;

@Path("/ycUcMember")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IYCUcMembersSV {
	@POST
	@Path("/queryUcMember")
	UcMembersInfo queryUcMember(Integer userID) throws BusinessException,SystemException;
	
}
