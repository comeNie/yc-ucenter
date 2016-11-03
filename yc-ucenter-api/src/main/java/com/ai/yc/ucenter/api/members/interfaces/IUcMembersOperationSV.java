package com.ai.yc.ucenter.api.members.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.yc.ucenter.api.members.param.UcMembersResponse;
import com.ai.yc.ucenter.api.members.param.checke.UcMembersCheckEmailRequest;
import com.ai.yc.ucenter.api.members.param.checke.UcMembersCheckeMobileRequest;
import com.ai.yc.ucenter.api.members.param.editemail.UcMembersEditEmailRequest;
import com.ai.yc.ucenter.api.members.param.editmobile.UcMembersEditMobileRequest;
import com.ai.yc.ucenter.api.members.param.editpass.UcMembersEditPassRequest;
import com.ai.yc.ucenter.api.members.param.get.UcMembersGetRequest;
import com.ai.yc.ucenter.api.members.param.get.UcMembersGetResponse;
import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginRequest;
import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginResponse;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersActiveRequest;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersGetOperationcodeRequest;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersGetOperationcodeResponse;
import com.ai.yc.ucenter.api.members.param.register.UcMembersRegisterRequest;

/**
 * Ucenter用户查询服务<br>
 * Date: 2016年11月1日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 *
 * @author mengbo
 */
@Path("/ucmembersOperation")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IUcMembersOperationSV {

   	/**
     * 操作码生成接口
     * <p/>
     *
     *
     * @param condition
     * @return
     * @throws BusinessException,SystemException
     * @ApiDocMethod
     * @ApiCode UC_0009
     * @RestRelativeURL ucmembers/ucGetOperationcode
	 */
	@POST
	@Path("/ucGetOperationcode")
	UcMembersGetOperationcodeResponse ucGetOperationcode(UcMembersGetOperationcodeRequest request) throws BusinessException,SystemException;
	/**
     * 操作码验证/激活接口
     * <p/>
     *
     *
     * @param condition
     * @return
     * @throws BusinessException,SystemException
     * @ApiDocMethod
     * @ApiCode UC_0010
     * @RestRelativeURL ucmembers/ucActiveMember
	 */
	@POST
	@Path("/ucActiveMember")
	UcMembersResponse ucActiveMember(UcMembersActiveRequest request) throws BusinessException,SystemException;
    
   
}
