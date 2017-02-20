package com.ai.yc.ucenter.api.ucpubilc.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersActiveRequest;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersGetOperationcodeRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.PubResponse;
import com.ai.yc.ucenter.api.ucpubilc.param.UcActiveMemberRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.UcActiveMemberResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcGetOperationcodeRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.UcGetOperationcodeResp;

/**
 * Ucenter用户查询服务<br>
 * Date: 2016年11月1日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 *
 * @author mengbo
 */
@Path("/ucpublic")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IUcPublicOperationSV {

   	/**
     * 操作码生成接口
     * <p/>
     *
     *
     * @param condition
     * @return
     * @throws BusinessException,SystemException
     * @ApiDocMethod
     * @ApiCode UCPUB_0009
     * @RestRelativeURL ucpublic/ucGetOperationcode
	 */
	@POST
	@Path("/ucGetOperationcode")
	PubResponse<UcGetOperationcodeResp> ucGetOperationcode(UcGetOperationcodeRequest request) throws BusinessException,SystemException;
	/**
     * 操作码验证/激活接口
     * <p/>
     *
     *
     * @param condition
     * @return
     * @throws BusinessException,SystemException
     * @ApiDocMethod
     * @ApiCode UCPUB_0010
     * @RestRelativeURL ucpublic/ucActiveMember
	 */
	@POST
	@Path("/ucActiveMember")
	PubResponse<UcActiveMemberResp> ucActiveMember(UcActiveMemberRequest request) throws BusinessException,SystemException;
    
   
}
